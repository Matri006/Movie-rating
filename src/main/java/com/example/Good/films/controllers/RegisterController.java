package com.example.Good.films.controllers;



import com.example.Good.films.models.User;
import com.example.Good.films.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request){
        try{
            userService.registerUser(username, email, password, "user");
            System.out.print("Имя пользователя: " + username);
            System.out.print("Эмаил: " + email);
            System.out.print("Пароль: " + password);
            model.addAttribute("successMessage", "Registration successful! Please log in.");
            request.getSession().invalidate();
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
    @PostMapping("/settings")
    public String settings(@RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword){
        userService.updateUser(username, password, repeatPassword);
        return "redirect:/home";
    }

}
