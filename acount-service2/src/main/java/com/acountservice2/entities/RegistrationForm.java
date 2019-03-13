package com.acountservice2.entities;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String email;
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                firstName, lastName, login, passwordEncoder.encode(password), email);
    }

}
