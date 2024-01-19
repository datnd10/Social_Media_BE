package com.social.BackEnd.service;

import com.social.BackEnd.models.Chat;
import com.social.BackEnd.models.Message;
import com.social.BackEnd.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
