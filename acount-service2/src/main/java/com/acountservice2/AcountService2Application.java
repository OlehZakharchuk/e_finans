package com.acountservice2;

import com.acountservice2.config.AccountProps;
import com.acountservice2.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableConfigurationProperties(AccountProps.class)
@EnableScheduling
public class AcountService2Application {
    @Autowired
    EmailService emailService;
    public static void main(String[] args) {

        SpringApplication.run(AcountService2Application.class, args
        );
    }

}
