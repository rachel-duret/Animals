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
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

// Show register form
    @GetMapping("/auth/register")
    public String renderRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";

    }
//Show login form
    @GetMapping("/auth/login")
    public String renderLoginForm(Model model){

        User user = new User();
        model.addAttribute("user", user);
        return "login";

    }
// Add one new user
    @PostMapping("/auth/register")
    public String register(@Valid @ModelAttribute("user")User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }
        User encodeUser = new User();
        encodeUser.setPassword(passwordEncoder.encode(user.getPassword()));
        encodeUser.setFirstname(user.getFirstname());
        encodeUser.setLastname(user.getLastname());
        userService.addOneUser(encodeUser);
        return "redirect:/auth/login";
    }

//    Check Login user
    @PostMapping("/auth/login")
    public  String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.hasErrors());
            return "login";

        }
        System.out.println(user.getPassword());
        UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
                new UsernamePasswordAuthenticationToken(user.getFirstname(), user.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(authentication.isAuthenticated());
        return  "redirect:/animals";
    }
}
