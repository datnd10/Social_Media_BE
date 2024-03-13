package com.social.BackEnd.repository;

import com.social.BackEnd.models.Post;
import com.social.BackEnd.models.Story;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;



public interface StoryRepository extends JpaRepository<Story, Integer> {

    @Query("select s from Story s where s.user.id = :userId and s.isDeleted = false")
    List<Story> findStoryByUserId(Integer userId, Sort sortByCreatedAtDesc);

    @Query("SELECT s FROM Story s WHERE (s.user.id IN :followings AND s.createdAt > :oneDayAgo and s.isDeleted = false)  ORDER BY s.createdAt DESC")
    List<Story> findByUserInAndCreatedAtAfterOrUserIdOrderByCreatedAtDesc(
            @Param("followings") List<Integer> followings,
            @Param("oneDayAgo") LocalDateTime oneDayAgo
    );

    @Query("SELECT s FROM Story s WHERE (s.user.id = :userId AND s.createdAt > :oneDayAgo and s.isDeleted = false)  ORDER BY s.createdAt DESC")
    List<Story> findStoriesByUser(
            @Param("userId") Integer userId,
            @Param("oneDayAgo") LocalDateTime oneDayAgo
    );

    @Query("SELECT s FROM Story s WHERE s.isDeleted = false")
    List<Story> findAllAvailableStories();
}
