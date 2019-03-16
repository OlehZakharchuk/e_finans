package com.acountservice2.controllers;

import com.acountservice2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainpage")
public class LoginController {


    @GetMapping
    public String logInUser(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("nameOfLogedUser", user.getFirstName());
        model.addAttribute("loginOfLogedUser", user.getLogin());

        return "main_page";
    }
}
