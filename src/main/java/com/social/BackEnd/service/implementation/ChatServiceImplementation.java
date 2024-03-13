package com.social.BackEnd.service.implementation;

import com.social.BackEnd.models.Chat;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.ChatRepository;
import com.social.BackEnd.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(User reqUser, User targetUser) {
        Chat isExist = findChatByUsersId(reqUser, targetUser);
        if (isExist != null) {
            return isExist;
        }
        Chat chat = new Chat();
        chat.getUsers().add(reqUser);
        chat.getUsers().add(targetUser);
        chat.setTime_stamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if (chat.isEmpty()) {
            throw new Exception("chat not found with id " + chatId);
        }

        return chat.get();
    }

    @Override
    public List<Chat> findUserChats(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }

    @Override
    public Chat findChatByUsersId(User reqUser, User targetUser) {
        return chatRepository.findChatByUsersId(reqUser, targetUser);
    }
}
