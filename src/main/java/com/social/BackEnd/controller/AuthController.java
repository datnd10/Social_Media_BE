package com.social.BackEnd.controller;

import com.social.BackEnd.config.jwtProvider;
import com.social.BackEnd.models.User;
import com.social.BackEnd.repository.UserRepository;
import com.social.BackEnd.request.LoginRequest;
import com.social.BackEnd.response.AuthResponse;
import com.social.BackEnd.service.CustomerUserDetailService;
import com.social.BackEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User userExist = userRepository.findByEmail(user.getEmail());
        if(userExist != null) {
            throw new Exception("user already exist with email " + user.getEmail());
        }


        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setAvatar("https://th.bing.com/th/id/OIP._BXCcqxwmsduYNCJj2XDtgHaHa?rs=1&pid=ImgDetMain");
        newUser.setBio("");
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token= jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"register success");
        return res;
    }

   @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"login success");
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(email);
        if(userDetails == null) {
            throw new BadCredentialsException("invalid email");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
}
