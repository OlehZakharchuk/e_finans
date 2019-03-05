package com.acountservice2.dao;

import com.acountservice2.entities.UserEntity;
import com.acountservice2.repositories.UserRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Service
public interface UserDao {

    UserEntity findByLoginAndPassword(String login, String pass);

}
