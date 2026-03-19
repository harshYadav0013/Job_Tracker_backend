package com.Harsh.jpaTutorial.controller;

import com.Harsh.jpaTutorial.model.User;
import com.Harsh.jpaTutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }


    @PostMapping("/api/register")
    @ResponseBody
    public ResponseEntity<?> registerUserApi(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok().body("{\"message\": \"registered\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"Email already in use\"}");
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}