package com.acountservice2.services;

import com.acountservice2.entities.User;

import java.util.Optional;

public interface UserServiceInterface {
    public Optional<User> findUserByEmail(String email);
    public Optional<User> findUserByResetToken(String resetToken);
    public void save(User user);
}
