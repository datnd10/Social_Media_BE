package com.social.BackEnd.service;

import com.social.BackEnd.exception.UserException;
import com.social.BackEnd.models.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post, Integer userId) throws Exception;
    String deletePost(Integer postId, Integer userId) throws Exception;
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId) throws Exception;
    List<Post> findAllPosts();

    Post savedPost(Integer postId, Integer userId) throws Exception;
    Post likePost(Integer postId, Integer userId) throws Exception;

    List<Post> findBySavedBy(Integer userId) throws UserException;
}
