package com.social.BackEnd.controller;


import com.social.BackEnd.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class NontificationRealTime {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/notify/{userId}")
    public void sendNotification(@DestinationVariable String userId, @Payload Notification message) {
        // Check if the userId matches the owner of the post
        if (Objects.equals(userId, String.valueOf(message.getToUser().getId()))) {
            simpMessagingTemplate.convertAndSendToUser(userId, "/notification", message);
        }
    }
}
