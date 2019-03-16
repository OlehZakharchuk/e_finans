package com.acountservice2.controllers;

import com.acountservice2.entities.RegistrationForm;
import com.acountservice2.repositories.UserRepository;
import com.acountservice2.validation.RegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RegistrationValidator registrationValidator;
    @GetMapping
    public String registration(RegistrationForm registrationForm){
        return "register_page";
    }
    @PostMapping
    public String registerNewUser(@Valid RegistrationForm registrationForm, BindingResult bindingResult){
        registrationValidator.validate(registrationForm, bindingResult);
        if(bindingResult.hasErrors())
            return "register_page";

        log.info("rejestracja: "+ registrationForm);
        userRepo.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}