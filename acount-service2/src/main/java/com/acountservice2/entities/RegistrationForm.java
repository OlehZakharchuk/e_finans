package com.acountservice2.entities;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;

@Data
public class RegistrationForm {

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String email;
    public UserEntity toUser(PasswordEncoder passwordEncoder) {
        return new UserEntity(
                firstName, lastName, login, passwordEncoder.encode(password), email);
    }

}
