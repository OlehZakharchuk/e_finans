package com.acountservice2.services;

import com.acountservice2.entities.UserEntity;
import com.acountservice2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRepositoryUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(s);
        if(user!=null)
            return user;
        throw new UsernameNotFoundException("User: " + s + " not found");
    }

}
