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
            User user = userRepository.findByLoginOrEmail(form.getLogin(), form.getEmail());
            log.info("Sprawdzanie unikatowosci username: "+ form);
            if(user!=null){
                if(user.getLogin().equals(form.getLogin()))
                //second argument login used on thymeleaf
                errors.rejectValue("login", "login" ,"Username already exists");
                if(user.getEmail().equals(form.getEmail())){
                errors.rejectValue("email", "email", "Account with this email already exists");
                }
            }




    }
}
