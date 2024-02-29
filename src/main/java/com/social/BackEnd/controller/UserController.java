package com.social.BackEnd.controller;

import com.social.BackEnd.exception.UserException;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.UserRepository;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) throws UserException {
        User user = userService.findUserById(userId);
        return user;
    }


    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user,  reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/api/users/follow/{userId}")
    public User followUserHandle(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId);
        return user;
    }

    @PutMapping("/api/users/unFollow/{userId}")
    public User unFollowUserHandle(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.unFollowUser(reqUser.getId(), userId);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
