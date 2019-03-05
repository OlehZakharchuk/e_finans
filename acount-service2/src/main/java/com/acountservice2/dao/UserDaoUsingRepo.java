package com.acountservice2.dao;

import com.acountservice2.entities.UserEntity;
import com.acountservice2.repositories.UserRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserDaoUsingRepo implements UserDao {

    @Autowired
    UserRepository userRepo;


    @Override
    public UserEntity findByLoginAndPassword(String login, String pass) {

            UserEntity user=null;
            try {
                user = userRepo.findByLoginAndPassword(login, pass);
            } catch (
                    NonUniqueResultException ex) {
                System.out.println("Istnieje dużo kilka takich rekordów");
            }

            return user;

    }
}
