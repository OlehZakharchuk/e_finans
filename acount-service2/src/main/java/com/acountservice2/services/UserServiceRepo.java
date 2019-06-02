package com.acountservice2.services;

import com.acountservice2.entities.User;
import com.acountservice2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceRepo implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
