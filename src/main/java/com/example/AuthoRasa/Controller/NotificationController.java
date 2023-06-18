package com.example.AuthoRasa.Controller;

import com.example.AuthoRasa.Model.LoginUpdate;
import com.example.AuthoRasa.Model.User;
import com.example.AuthoRasa.Model.WeightUpdate;
import com.example.AuthoRasa.PushNotificationService;
import com.example.AuthoRasa.Repos.LoginRepository;
import com.example.AuthoRasa.Repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Controller
public class NotificationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PushNotificationService pushNotificationService;
    private Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Scheduled(fixedDelay = 600000)
    public void sendNotificationLastLogin() {
        try {
            List<User> list = userRepository.findAll();
            for(User i : list) {
                List<LoginUpdate> loginUpdates = i.getListLogin();
                List<WeightUpdate> weightUpdates = i.getListWeight();
                if (!loginUpdates.isEmpty()) {
                    int sizeLoginUpdates = loginUpdates.size();
                    LocalDateTime time = loginUpdates.get(sizeLoginUpdates - 1).getTime_taken();
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(7);
                    if (now.isAfter(time)) {
                        pushNotificationService.sendNotification(i.getToken(),"وحشتنا يا بطل","طولت الغيبة");
                    }
                }
                if(!weightUpdates.isEmpty()){
                    int sizeLoginUpdates = weightUpdates.size();
                    LocalDateTime time = weightUpdates.get(sizeLoginUpdates - 1).getTime_taken();
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(7);
                    if (now.isAfter(time)) {
                        pushNotificationService.sendNotification(i.getToken(),"فينك يا بطل","بقالنا كتير معرفناش وزنك");
                    }
                }
                pushNotificationService.sendNotification(i.getToken(),"كمل يا بطل","قربنا على الحلم");
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
