package com.Alphanetworks.Animals.controller;

import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String renderRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";

    }

    @GetMapping("/login")
    public String renderLoginForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";

    }

    @PostMapping("/register/save")
    public String register(@ModelAttribute("user")User user){
        System.out.println(user.getFirstname());
        userService.addOneUser(user);
        return "redirect:/login";
    }
}
