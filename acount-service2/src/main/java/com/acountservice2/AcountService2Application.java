package com.acountservice2;

import com.acountservice2.config.AccountProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(AccountProps.class)
public class AcountService2Application {

    public static void main(String[] args) {
        SpringApplication.run(AcountService2Application.class, args);
    }

}
