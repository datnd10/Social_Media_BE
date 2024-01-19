package com.social.BackEnd.repository;

import com.social.BackEnd.models.Chat;
import com.social.BackEnd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUsersId(Integer userId);


    @Query("select c from Chat c where :reqUserId Member of c.users And :targetUserId Member of c.users")
    public Chat findChatByUsersId(@Param("reqUserId") User user, @Param("targetUserId") User targetUser);

}
