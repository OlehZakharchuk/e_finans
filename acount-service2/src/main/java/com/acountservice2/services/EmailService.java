package com.acountservice2.services;

import com.acountservice2.entities.User;
import com.acountservice2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private SpendingService spendingService;
    private UserRepository userRepository;

    public void sendMonthReview() throws MailException
    {


        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("zakharchuk.oleh@gmail.com");
        mail.setSubject("Testing Mail API");
        mail.setText("Hurray ! You have done that dude...");

        javaMailSender.send(mail);
    }
    public void sendDayReview() throws MailException
    {

        Iterable<User> users = userRepository.findAll();
        for(User user : users){
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("zakharchuk.oleh@gmail.com");
            mail.setSubject("Testing Mail API");
            mail.setText("Hurray ! You have done that dude...");
        }


        javaMailSender.send(mail);
    }
}