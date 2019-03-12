package com.acountservice2.controllers;

import com.acountservice2.entities.RegistrationForm;
import com.acountservice2.entities.UserEntity;
import com.acountservice2.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping
    public String registration(){
        return "register_page";
    }
    @PostMapping
    public String registerNewUser(RegistrationForm registrationForm){
        log.info("rejestracja: "+ registrationForm);
        userRepo.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}