package com.social.BackEnd.service.implementation;

import com.social.BackEnd.models.Chat;
import com.social.BackEnd.models.Message;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.ChatRepository;
import com.social.BackEnd.repository.MessageRepository;
import com.social.BackEnd.service.ChatService;
import com.social.BackEnd.service.MessageService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Message message = new Message();
        Chat chat = chatService.findChatById(chatId);

        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setTime_stamp(LocalDateTime.now());
        chat.getMessages().add(message);
        chatRepository.save(chat);
        Message newMessage =  messageRepository.save(message);
        return newMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatId(chatId);
    }
}
