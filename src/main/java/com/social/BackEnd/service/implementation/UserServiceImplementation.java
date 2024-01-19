package com.social.BackEnd.service.implementation;

import com.social.BackEnd.config.jwtProvider;
import com.social.BackEnd.exception.UserException;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.UserRepository;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("user not exist with userid" + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer reqUserid, Integer userId2) throws UserException {
        User reqUser = findUserById(reqUserid);
        User user2 = findUserById(userId2);
        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());
        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserException {
        Optional<User> user1 = userRepository.findById(userId);
        if(user1.isEmpty()) {
            throw new UserException("user not exist with userid" + userId);
        }

        User oldUser = user1.get();
        if(user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if(user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if(user.getPassword() != null) {
            oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getGender() != null) {
            oldUser.setGender(user.getGender());
        }
        User  updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String token) {
        String email = jwtProvider.getEmailFromJwtToken(token);
        User user = userRepository.findByEmail(email);
        return user;
    }
}
