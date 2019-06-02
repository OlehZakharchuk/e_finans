package com.acountservice2.repositories;

import com.acountservice2.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByLogin(String login);
    User findByLoginOrEmail(String login, String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
}
