package com.Alphanetworks.Animals.controller;

import com.Alphanetworks.Animals.models.User;
import com.Alphanetworks.Animals.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

// Show register form
    @GetMapping("/register")
    public String renderRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";

    }
//Show login form
    @GetMapping("/login")
    public String renderLoginForm(){
        return "login";
    }
// Add one new user
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user")User user, Model model, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "register";
        }
        userService.addOneUser(user);
        return "redirect:/login";
    }


}
