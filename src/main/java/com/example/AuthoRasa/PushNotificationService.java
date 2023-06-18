package com.example.AuthoRasa;

import com.example.AuthoRasa.firebase.FCMService;
import com.example.AuthoRasa.Model.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {

    @Value("#{${app.notifications.defaults}}")
    private Map<String, String> defaults;

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void sendNotification(String token,String title,String message){
        try {
            PushNotificationRequest a = getNotificationRequest(title,message);
            a.setToken(token);
            fcmService.sendMessageToToken(a);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }

    private PushNotificationRequest getNotificationRequest(String title,String message) {
        PushNotificationRequest request = new PushNotificationRequest(title,
                message,
                "common");
        return request;
    }

}
