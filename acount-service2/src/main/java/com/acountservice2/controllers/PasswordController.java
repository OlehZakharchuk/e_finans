package com.acountservice2.controllers;

import com.acountservice2.entities.User;
import com.acountservice2.repositories.UserRepository;
import com.acountservice2.services.EmailService;
import com.acountservice2.services.UserServiceInterface;
import com.acountservice2.services.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PasswordController {

    @Autowired
    UserServiceInterface userService;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String getForgotPage(){
        return "forgot_page";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String sendRestorePassToken(ModelAndView modelAndView,
                                       @RequestParam("email") String email, HttpServletRequest request){

        Optional<User> optionalUser = userService.findUserByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            user.setResetToken(UUID.randomUUID().toString());

            userService.save(user);
            String appUrl =request.getScheme() + "://" +
                    request.getServerName() +
                    ("http".equals(request.getScheme()) && request.getServerPort() == 80
                            || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":"
                            + request.getServerPort() )
                    +"/reset?token="
                    +user.getResetToken();

            emailService.sentResetToken(user.getEmail(), appUrl);
            modelAndView.addObject("successMessage", "A password reset link has been sent to " + email);
        }
        else
            modelAndView.addObject("successMessage", email + " doesn't exist, you can create new account");

        return "forgot_page";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String getResetPage(Model model, @RequestParam("token") String token){


        Optional<User> user = userService.findUserByResetToken(token);

        if (user.isPresent()) { // Token found in DB
            System.out.println("user token get: "+user.get().getResetToken());
            model.addAttribute("token", user.get().getResetToken());
        } else { // Token not found in DB
            model.addAttribute("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        return "reset_pass_page";
    }
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String resetPassword(Model model, @RequestParam Map<String, String> requestParams, RedirectAttributes redir){
        String token = requestParams.get("token");
        String password = requestParams.get("password");
        System.out.println(token);
        System.out.println(password);
        Optional<User> user = userService.findUserByResetToken(token);
        if (user.isPresent()) {

            User resetUser = user.get();
            resetUser.setPassword(passwordEncoder.encode(password));

            resetUser.setResetToken(null);
            userService.save(resetUser);

            return "login";

        } else {
            model.addAttribute("message", "Oops!  This is an invalid password reset link.");

        }

        return "reset_pass_page";
    }

}
