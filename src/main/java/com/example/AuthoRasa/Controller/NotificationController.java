package com.example.AuthoRasa.Controller;

import com.example.AuthoRasa.Model.*;
import com.example.AuthoRasa.PushNotificationService;
import com.example.AuthoRasa.Repos.LoginRepository;
import com.example.AuthoRasa.Repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Controller
public class NotificationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PushNotificationService pushNotificationService;
    private Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final String[] quotes ={"لا لا لا تستلم ابدا",
            "أفكار الإنسان هي ما تصنع مستقبله",
            "النجاح ليس إنجازاً بقدر ما هو قدرة مستمرة على الإنجاز",
            "الحياة إمّا أن تكون مُغامرة جرئية أو لا شيء"};

    @Scheduled(fixedDelay = 6000000)
    public void sendNotification1() {
        try {
            List<User> list = userRepository.findAll();
            for(User i : list) {
                Random rand = new Random();
                pushNotificationService.sendNotification(i.getToken(),"رفيعة",quotes[(rand.nextInt(quotes.length))]);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }
    @Scheduled(fixedDelay = 600000000)
    public void sendNotification2() {
        try {
            List<User> list = userRepository.findAll();
            for(User i : list) {
                List<LoginUpdate> loginUpdates = i.getListLogin();
                List<WeightUpdate> weightUpdates = i.getListWeight();
                List<LunchTime> lunchTimeList = i.getLunchTimeList();
                List<BreakfastTime> breakfastTimeList = i.getBreakfastTimeList();
                List<DinnerTime> dinnerTimeList = i.getDinnerTimeList();
                if (!loginUpdates.isEmpty()) {
                    int sizeLoginUpdates = loginUpdates.size();
                    LocalDateTime time = loginUpdates.get(sizeLoginUpdates - 1).getTime_taken();
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(7);
                    if (now.isAfter(time)) {
                        pushNotificationService.sendNotification(i.getToken(),"رفيعة","طولت الغيبة");
                    }
                }
                if(!weightUpdates.isEmpty()){
                    int sizeLoginUpdates = weightUpdates.size();
                    LocalDateTime time = weightUpdates.get(sizeLoginUpdates - 1).getTime_taken();
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(7);
                    if (now.isAfter(time)) {
                        pushNotificationService.sendNotification(i.getToken(),"رفيعة","بقالنا كتير معرفناش وزنك");
                    }
                }
                if(!lunchTimeList.isEmpty()){
                    int sizeLunchTimeList = lunchTimeList.size();
                    LocalDateTime time = lunchTimeList.get(sizeLunchTimeList - 1).getTime();
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(1);
                    if (now.isAfter(time)) {
                        pushNotificationService.sendNotification(i.getToken(),"رفيعة","معاد وجبة الغداء يا بطل");
                    }
                }
                if(!breakfastTimeList.isEmpty()){
                    int sizeLunchTimeList = breakfastTimeList.size();
                    LocalDateTime time = breakfastTimeList.get(sizeLunchTimeList - 1).getTime();
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(1);
                    if (now.isAfter(time)) {
                        pushNotificationService.sendNotification(i.getToken(),"رفيعة","معاد وجبة الفطار يا بطل");
                    }
                }
                if(!dinnerTimeList.isEmpty()){
                    int sizeLunchTimeList = dinnerTimeList.size();
                    LocalDateTime time = dinnerTimeList.get(sizeLunchTimeList - 1).getTime();
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusDays(1);
                    if (now.isAfter(time)) {
                        pushNotificationService.sendNotification(i.getToken(),"رفيعة","معاد وجبة العشاء يا بطل");
                    }
                }
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
