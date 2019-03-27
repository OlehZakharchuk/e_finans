package com.acountservice2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Data
@ConfigurationProperties(prefix = "account.spending")
@Configuration
public class AccountProps {

    private int amountOfRecords = 2;


}
