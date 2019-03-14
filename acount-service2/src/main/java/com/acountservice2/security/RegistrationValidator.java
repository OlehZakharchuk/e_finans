package com.acountservice2.security;

import com.acountservice2.entities.RegistrationForm;
import com.acountservice2.entities.User;
import com.acountservice2.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class RegistrationValidator implements Validator {

    @Autowired
    UserRepository userRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
            RegistrationForm form = (RegistrationForm) o;
            User user = userRepository.findByLogin(form.getLogin());
            log.info("Sprawdzanie unikatowosci username: "+ form);
            if(user!=null && form.getLogin().equals(user.getLogin())){
                //second argument login used on thymeleaf
                errors.rejectValue("login", "login" ,"Username already exists");

            }

    }
}
