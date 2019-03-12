package com.acountservice2.controllers;

import com.acountservice2.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public String toHome() {
        return "login";
    }

    @RequestMapping("/main_page")
    public String mainPage(){
        return "main_page";
    }
    @PostMapping("/login")
    public String login(@RequestParam("inname") String login, @RequestParam("inpass") String pass, Model model) {

        return "main_page";


    }

}


