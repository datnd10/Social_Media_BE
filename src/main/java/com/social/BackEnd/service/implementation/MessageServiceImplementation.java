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
    public Message createMessage(User user, Integer chatId, Message message) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        Message newMessage = new Message();
        newMessage.setContent(message.getContent());
        newMessage.setImage(message.getImage());
        newMessage.setChat(chat);
        newMessage.setUser(user);
        newMessage.setTime_stamp(LocalDateTime.now());

        chat.getMessages().add(newMessage);
        chatRepository.save(chat);

        return messageRepository.save(newMessage);
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatId(chatId);
    }
}
