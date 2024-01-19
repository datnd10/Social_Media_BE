package com.social.BackEnd.controller;

import com.social.BackEnd.models.Reel;
import com.social.BackEnd.models.User;
import com.social.BackEnd.service.ReelService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelController {

    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String token) {
        User user = userService.findUserByJwt(token);
        Reel createdReel = reelService.createReel(reel, user);
        System.out.println(createdReel);
        return createdReel;
    }
    @GetMapping("/api/reels")
    public List<Reel> findAllReels() {
        return reelService.findAllReels();
    }

    @GetMapping("/api/reels/user/{userid}")
    public List<Reel> findUserReels(@PathVariable Integer userid) throws Exception {
        List<Reel> reels = reelService.findUserReels(userid);
        return reels;
    }

}
