package com.acountservice2.controllers;

import com.acountservice2.dao.UserDao;
import com.acountservice2.entities.UserEntity;
import com.acountservice2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeletingController {

    @Autowired
    UserDao userDao;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginController loginController;

    @RequestMapping(value = "deleteaccount")
    public String deleteAccount(@RequestParam("inname") String inname, @RequestParam("inpass") String inpass){

        UserEntity userToDelete = userDao.findByLoginAndPassword(inname, inpass);
        userRepository.delete(userToDelete);
     return loginController.toHome();

    }
}
