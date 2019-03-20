package com.acountservice2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "account.spending")
@Data
public class AccountProps {

    private int amountOfRecords = 20;

}
