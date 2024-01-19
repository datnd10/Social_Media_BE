package com.social.BackEnd.service;


import com.social.BackEnd.models.Chat;
import com.social.BackEnd.models.User;


import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User targetUser);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUserChats(Integer userId);

}
