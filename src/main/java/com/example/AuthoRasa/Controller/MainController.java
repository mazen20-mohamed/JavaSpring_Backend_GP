package com.example.AuthoRasa.Controller;
import com.example.AuthoRasa.Model.PushNotificationRequest;
import com.example.AuthoRasa.Model.PushNotificationResponse;
import com.example.AuthoRasa.PushNotificationService;
import com.example.AuthoRasa.Repos.LoginRepository;
import com.example.AuthoRasa.Model.LoginUpdate;
import com.example.AuthoRasa.Model.User;
import com.example.AuthoRasa.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping(path="/register")
    public ResponseEntity<String> register (@RequestBody User n) {
            try {
                n.setId(userRepository.count()+1);
                int bmr = 0;
                // calculate calories
                if(n.getGender().equalsIgnoreCase("men")){
                    bmr = (int)((10*n.getWeight()) + (6.25*n.getHeight()) - (5*n.getAge()) + 5);
                }
                else{
                    bmr = (int)((10*n.getWeight()) + (6.25*n.getHeight()) - (5*n.getAge()) - 161);
                }
                bmr = (int)(bmr * n.getActivationRate());
                n.setCalories(bmr);
//                LoginUpdate loginUpdate = new LoginUpdate();
//                loginUpdate.setId((loginRepository.count()+1));
//                loginUpdate.setTime_taken(LocalDateTime.now());
//                loginUpdate.setUser(n);
//                loginRepository.save(loginUpdate);
//                n.addInListLogin(loginUpdate);
                userRepository.save(n);
                String string = "saved";
                return new ResponseEntity<>(string , HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
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
