package com.social.BackEnd.service.implementation;

import com.social.BackEnd.models.Comment;
import com.social.BackEnd.models.Post;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.CommentRepository;
import com.social.BackEnd.repository.PostRespository;
import com.social.BackEnd.service.CommentService;
import com.social.BackEnd.service.PostService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRespository postRespository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);
        postRespository.save(post);
        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt = commentRepository.findById(commentId);
        if(opt.isEmpty()) {
            throw new Exception("comment not found with id" + commentId);
        }
        return opt.get();
    }

    @Override
    public Comment likeComment(Integer postId, Integer userId) throws Exception {
        Comment comment = findCommentById(postId);
        User user = userService.findUserById(userId);
        if(!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        }
        else {
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }
}
