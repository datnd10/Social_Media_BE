package com.social.BackEnd.controller;

import com.social.BackEnd.models.Comment;
import com.social.BackEnd.models.User;
import com.social.BackEnd.service.CommentService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment created =  commentService.createComment(comment, postId, user.getId());
        return created;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt, @PathVariable Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment created =  commentService.likeComment(commentId, user.getId());
        return created;
    }

}
