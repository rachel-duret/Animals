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

    private UserService userService;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

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
    public String register(@Valid @ModelAttribute("user")User user,  BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "register";
        }
        User encodeUser = new User();
        encodeUser.setPassword(passwordEncoder.encode(user.getPassword()));
        encodeUser.setFirstname(user.getFirstname());
        encodeUser.setLastname(user.getLastname());
        userService.addOneUser(encodeUser);
        return "redirect:/login";
    }


}
