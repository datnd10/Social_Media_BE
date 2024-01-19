package com.social.BackEnd.controller;

import com.social.BackEnd.models.Message;
import com.social.BackEnd.models.User;
import com.social.BackEnd.service.MessageService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @PathVariable Integer chatId, @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwt(token);
        Message message = messageService.createMessage(user, chatId, req);
        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatMessage(@PathVariable Integer chatId, @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwt(token);
        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }

}
