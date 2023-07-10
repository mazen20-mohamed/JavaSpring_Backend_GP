package com.example.AuthoRasa.Controller;
import com.example.AuthoRasa.Repos.LoginRepository;
import com.example.AuthoRasa.Model.LoginUpdate;
import com.example.AuthoRasa.Model.User;
import com.example.AuthoRasa.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
// localhost/demo/login
@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping(path="/register")
    public ResponseEntity<String> register (@RequestBody User user) {
            try {
                user.setId(userRepository.count()+1);
                int bmr = 0;
                // calculate calories
                if(user.getGender().equalsIgnoreCase("men")){
                    bmr = (int)((10*user.getWeight()) + (6.25*user.getHeight()) - (5*user.getAge()) + 5);
                }
                else{
                    bmr = (int)((10*user.getWeight()) + (6.25*user.getHeight()) - (5*user.getAge()) - 161);
                }
                bmr = (int)(bmr * user.getActivationRate());
                bmr-=300;
                user.setCalories(bmr);
                userRepository.save(user);
                String string = "saved";
                return new ResponseEntity<>(string , HttpStatus.OK); // 200
            }
            catch (Exception e) {
                return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST); // 400
            }
        }
        @GetMapping(path="/login")
        public ResponseEntity<Long> login(@RequestParam String email ,@RequestParam String password) {
            try {
                Optional<User> user = userRepository.findByEmailAndPassword(email,password);
                LoginUpdate loginUpdate = new LoginUpdate();
                loginUpdate.setId((loginRepository.count()+1));
                loginUpdate.setTime_taken(LocalDateTime.now());
                loginUpdate.setUser(user.get());
                loginRepository.save(loginUpdate);
                user.get().addInListLogin(loginUpdate);
                userRepository.save(user.get());
                return new ResponseEntity<>(user.get().getId(), HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }
}
