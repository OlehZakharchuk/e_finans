package com.acountservice2.repositories;

import com.acountservice2.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByLogin(String login);
}
