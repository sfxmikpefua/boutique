package com.michael.controller;

import com.michael.model.User;
import com.michael.service.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    IUserService userService;

    @GetMapping("/login")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {

        model.addAttribute("required_email", null);
        model.addAttribute("invalid_user", null);

        if (user.getEmail().isEmpty()) {
            model.addAttribute("required_email", "Email Field is Required");

            return "login";
        }
        User authenticatedUser = userService.login(user.getEmail(), user.getPassword());

        if (authenticatedUser == null) {
            model.addAttribute("invalid_user", "User Not Found! Check email or password");
            System.out.println("I am Not FOund");
            return "login";
        }

        System.out.println("I am found");

        httpSession.setAttribute("user_session", authenticatedUser);
        httpSession.setMaxInactiveInterval(1000);

        if (authenticatedUser.getType().equals("customer")) {
            return "redirect:/confirmation";
        }

        return  "redirect:/admin/dashboard";


    }
}