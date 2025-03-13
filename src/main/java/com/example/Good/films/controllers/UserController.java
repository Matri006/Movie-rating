package com.example.Good.films.controllers;

import com.example.Good.films.models.User;
import com.example.Good.films.repositories.UserRepository;

import com.example.Good.films.services.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    UserController(UserRepository userRepository, UserService userService){
        this.userRepository = userRepository;
        this.userService = userService;

    }

    public String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new UsernameNotFoundException("Пользователь не аутентифицирован");
        }
        return authentication.getName();

    }






}
