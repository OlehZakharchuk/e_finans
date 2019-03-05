package com.acountservice2.controllers;

import com.acountservice2.entities.UserEntity;
import com.acountservice2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    LoginController loginController;
    @RequestMapping("register")
    public String registerNewUser(@RequestParam("firstname") String firstname, @RequestParam("lastname")String lastname,
                                  @RequestParam("inpass") String inpass, @RequestParam("inname") String inname,
                                  @RequestParam("inemail") String inemail){
        UserEntity newUser = new UserEntity(firstname, lastname, inname, inpass, inemail);
        userRepo.save(newUser);
        return loginController.toHome();
    }
}