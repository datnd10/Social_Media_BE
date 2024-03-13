package com.social.BackEnd.controller;


import com.social.BackEnd.models.Notification;
import com.social.BackEnd.models.User;
import com.social.BackEnd.response.ApiResponse;
import com.social.BackEnd.service.NontificationService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NontificationController {
    @Autowired
    private NontificationService nontificationService ;

    @Autowired
    private UserService userService;

    @PostMapping("/api/nontification/post/{postId}/to/{toUserId}")
    public ResponseEntity<Notification> createNontification(@RequestBody Notification notification, @PathVariable Integer postId, @PathVariable Integer toUserId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Notification newNotification = nontificationService.createNontification(notification, toUserId, reqUser.getId(), postId);
        return new ResponseEntity<>(newNotification, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/nontification/{nontificationId}")
    public ResponseEntity<ApiResponse> deleteNontification(@PathVariable Integer nontificationId) throws Exception {
        String message = nontificationService.deleteNontification(nontificationId);
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/api/nontification/user/{userId}")
    public ResponseEntity<List<Notification>> findNontificationById(@PathVariable Integer userId) throws Exception {
        List<Notification> nontifications = nontificationService.findNontificationByUserId(userId);
        return new ResponseEntity<List<Notification>>(nontifications, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/nontification/watch/{nontificationId}")
    public ResponseEntity<Notification> watchNontificationHandler(@PathVariable Integer nontificationId) throws Exception {
        Notification notification = nontificationService.watchNontification(nontificationId);
        return new ResponseEntity<Notification>(notification, HttpStatus.ACCEPTED);
    }
}
