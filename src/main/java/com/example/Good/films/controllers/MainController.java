package com.example.Good.films.controllers;

import com.example.Good.films.models.Film;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainController {
    @GetMapping(value = {"/", "/login"})
    public String login(Model model){
        model.addAttribute("message", "Hello, world!");
        return "login";
    }

    @GetMapping ("/register")
    public String register(Model model){
        model.addAttribute("message", "It'a a register page");
        return "/register";

    }
    @GetMapping("/settings")
    public String settings(Model model){
        model.addAttribute("message", "It's a settings page");
        return "/settings";
    }
    @GetMapping("/add-movie")
    public String addMovie(Model model){
        model.addAttribute("message", "It's a add-movie page");
        return "/add-movie";
    }

}
