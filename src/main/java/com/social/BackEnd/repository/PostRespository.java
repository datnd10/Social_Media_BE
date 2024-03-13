package com.social.BackEnd.repository;

import com.social.BackEnd.models.Post;
import com.social.BackEnd.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRespository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.isDeleted = false ")
    List<Post> findPostByUserId(@Param("userId") Integer userId, Sort sort);



    @Query("SELECT p FROM Post p WHERE :user MEMBER OF p.savedBy AND p.isDeleted = false")
    List<Post> findBySavedBy(@Param("user") User user, Sort sort);



    @Query("SELECT p FROM Post p WHERE p.isDeleted = false ")
    List<Post> getAllByPostAvailable(Sort sort);
}
