package com.social.BackEnd.controller;

import com.social.BackEnd.models.Reel;
import com.social.BackEnd.models.User;
import com.social.BackEnd.response.ApiResponse;
import com.social.BackEnd.service.ReelService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelController {

    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public ResponseEntity<Reel> createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String token) {
        User user = userService.findUserByJwt(token);
        Reel createdReel = reelService.createReel(reel, user);
        return new ResponseEntity<Reel>(createdReel, HttpStatus.ACCEPTED);
    }

    @PutMapping ("/api/reels/{reelId}")
    public ResponseEntity<Reel> deleteReel(@PathVariable Long reelId) throws Exception {
        Reel reel = reelService.deleteReel(reelId);
        return new ResponseEntity<Reel>(reel, HttpStatus.OK);
    }
    @GetMapping("/api/reels")
    public ResponseEntity<List<Reel>> findAllReels() {
        List<Reel> reels = reelService.findAllReels();
        return new ResponseEntity<List<Reel>>(reels, HttpStatus.OK);
    }

    @GetMapping("/api/reels/user/{userid}")
    public ResponseEntity<List<Reel>> findUserReels(@PathVariable Integer userid) throws Exception {
        List<Reel> reels = reelService.findUserReels(userid);
        return new ResponseEntity<List<Reel>>(reels, HttpStatus.OK);
    }
}
