package com.social.BackEnd.service;

import com.social.BackEnd.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;
    public Comment likeComment(Integer postId, Integer userId) throws Exception;

}
