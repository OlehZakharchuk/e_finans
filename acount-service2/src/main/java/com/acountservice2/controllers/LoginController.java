package com.acountservice2.controllers;


import com.acountservice2.dao.UserDao;
import com.acountservice2.entities.UserEntity;
import com.acountservice2.repositories.UserRepository;
import com.acountservice2.services.HibernateUtility;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/")
    public String toHome() {
        return "home";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("inname") String login, @RequestParam("inpass") String pass, Model model) {
        UserEntity user = userDao.findByLoginAndPassword(login,pass);


        if(user!=null){
            model.addAttribute("nameOfLogedUser", user.getLastName());
            model.addAttribute("loginOfLogedUser", user.getLogin());

            return "main_page";
    }
        return "home";
    }

}


