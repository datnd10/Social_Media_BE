package com.social.BackEnd.controller;

import com.social.BackEnd.models.Story;
import com.social.BackEnd.models.User;
import com.social.BackEnd.response.ApiResponse;
import com.social.BackEnd.service.StoryService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public ResponseEntity<Story> createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Story createdPost = storyService.createStory(story, reqUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/story/{storyId}")
    public ResponseEntity<ApiResponse> deleteStory(@PathVariable Integer storyId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = storyService.deleteStory(storyId, reqUser.getId());
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/api/story/{storyId}")
    public ResponseEntity<Story> findStoryByIdHandler(@PathVariable Integer storyId) throws Exception {
        Story post = storyService.findStoryById(storyId);
        return new ResponseEntity<Story>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/story/user/{userId}")
    public ResponseEntity<List<Story>> findUserStory(@PathVariable Integer userId) {
        List<Story> story = storyService.findStoryByUserId(userId);
        return new ResponseEntity<List<Story>>(story, HttpStatus.OK);
    }

    @GetMapping("/api/story/following")
    public ResponseEntity<List<Story>> findStoryByFollowings(@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        List<Story> story = storyService.findByFollowings(reqUser.getId());
        return new ResponseEntity<List<Story>>(story, HttpStatus.OK);
    }

    @GetMapping("/api/story")
    public ResponseEntity<List<Story>> findAllStory() {
        List<Story> story = storyService.findAllStoryAvailable();
        return new ResponseEntity<List<Story>>(story, HttpStatus.OK);
    }

    @PutMapping("/api/story/like/{storyId}")
    public ResponseEntity<Story> likeStoryHandler(@PathVariable Integer storyId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Story story = storyService.reactStory(storyId, reqUser.getId());
        return new ResponseEntity<Story>(story, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/story/watch/{storyId}")
    public ResponseEntity<Story> watchStoryHandler(@PathVariable Integer storyId,@RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Story story = storyService.watchStory(storyId, reqUser.getId());
        return new ResponseEntity<Story>(story, HttpStatus.ACCEPTED);
    }
}
