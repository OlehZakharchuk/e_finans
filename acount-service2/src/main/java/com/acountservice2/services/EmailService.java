package com.acountservice2.services;

import com.acountservice2.entities.User;
import com.acountservice2.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpendingService spendingService;
    @Autowired
    private LimitService limitService;
    @Autowired
    private UserRepository userRepository;

  // @Scheduled(cron = "10 * * * * * ")  //for test
    @Scheduled(cron = "0 0 19 28-31 * ?")
    public void sendMonthReport() throws MailException
    {
        Calendar c = Calendar.getInstance();
        if(!(c.get(Calendar.DAY_OF_MONTH) == c.getActualMaximum(Calendar.DAY_OF_MONTH)))
            return;
        Iterable<User> users = userRepository.findAll();
        for(User user : users) {
            if(user.isMonthReport()) {
                log.info("sending monthly email: "+ user.getEmail());
                double monthSpending = spendingService.getMonthSpending(user);
                double limit = limitService.getMonthLimit(user);

                String mailContent = "Hello, your month report: \n"+
                        "Waisted: " + monthSpending+ "\n"+
                        "Limit: " + limit + "\n"+
                        "Balance according your limit: " + (limit - monthSpending)+"\n" +
                        "Balance according to year limit: " + (limitService.getYearLimit(user)-spendingService.getYearSpending(user))+
                        "Average spending: " + monthSpending/c.getActualMaximum(Calendar.DAY_OF_MONTH)+" PLN per day";


                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user.getEmail());
                mail.setSubject("Your month report for " + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH ));
                mail.setText(mailContent);

                javaMailSender.send(mail);
            }
        }

    }
   @Scheduled(cron = "0 0 19 ? * SUN")
    public void sendWeeklyReport() throws MailException
    {
        Calendar c = Calendar.getInstance();
        Iterable<User> users = userRepository.findAll();
        for(User user : users){
            if(user.isWeeklyReport()) {
                log.info("sending weekly report email: "+ user.getEmail());
                double weeklySpending = spendingService.getLastWeekSpending(user);
                double limit = limitService.getMonthLimit(user);

                String mailContent = "Hello, your weekly report: \n"+
                        "Waisted: " + weeklySpending+ "\n"+
                        "Month limit: " + limit + "\n"+
                        "Balance according to year limit: " + (limitService.getYearLimit(user)-spendingService.getYearSpending(user))+"\n"+
                        "Average weekly spending: " + Math.round(weeklySpending/7)+" PLN per day";


                SimpleMailMessage mail = new SimpleMailMessage();
                mail.setTo(user.getEmail());
                mail.setSubject("Your weekly report for current week");
                mail.setText(mailContent);
                javaMailSender.send(mail);
            }
        }



    }
}