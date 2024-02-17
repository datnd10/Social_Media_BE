package com.social.BackEnd.controller;

import com.social.BackEnd.models.Chat;
import com.social.BackEnd.models.User;
import com.social.BackEnd.request.CreateChatRequest;
import com.social.BackEnd.service.ChatService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String token, @RequestBody CreateChatRequest req) throws Exception {
            User user = userService.findUserByJwt(token);
            User user2 = userService.findUserById(req.getUserId());
            Chat chat = chatService.createChat(user, user2);
            return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUserChats(@RequestHeader("Authorization") String token) {
        User user = userService.findUserByJwt(token);
        List<Chat> chats = chatService.findUserChats(user.getId());
        return chats;
    }
}
