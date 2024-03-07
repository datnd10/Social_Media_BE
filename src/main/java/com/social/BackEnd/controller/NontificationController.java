package com.social.BackEnd.controller;


import com.social.BackEnd.models.Nontification;
import com.social.BackEnd.models.Story;
import com.social.BackEnd.models.User;
import com.social.BackEnd.response.ApiResponse;
import com.social.BackEnd.service.NontificationService;
import com.social.BackEnd.service.StoryService;
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

    @PostMapping("/api/nontification/post/{postId}/from/{fromUserId}")
    public ResponseEntity<Nontification> createNontification(@RequestBody Nontification nontification, @PathVariable Integer postId, @PathVariable Integer fromUserId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Nontification newNontification = nontificationService.createNontification(nontification, reqUser.getId(), fromUserId, postId);
        return new ResponseEntity<>(newNontification, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/nontification/{nontificationId}")
    public ResponseEntity<ApiResponse> deleteNontification(@PathVariable Integer nontificationId) throws Exception {
        String message = nontificationService.deleteNontification(nontificationId);
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/api/nontification")
    public ResponseEntity<List<Nontification>> findNontificationById(@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<Nontification> nontifications = nontificationService.findNontificationByUserId(reqUser.getId());
        return new ResponseEntity<List<Nontification>>(nontifications, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/nontification/watch/{nontificationId}")
    public ResponseEntity<Nontification> watchNontificationHandler(@PathVariable Integer nontificationId) throws Exception {
        Nontification nontification = nontificationService.watchNontification(nontificationId);
        return new ResponseEntity<Nontification>(nontification, HttpStatus.ACCEPTED);
    }
}
