package com.acountservice2.services;

import com.acountservice2.entities.Limit;
import com.acountservice2.repositories.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LimitServiceSD implements LimitService {

    @Autowired
    LimitRepository limitRepository;
    @Override
    public Limit update(Limit limitToSave) {
        Optional<Limit> limitDB = limitRepository.getByMonthAndYear(limitToSave.getMonth(), limitToSave.getYear());
        if(limitDB.isPresent()){
            // if already exist, update just amount
            limitDB.get().setAmount(limitToSave.getAmount());
            limitRepository.save(limitDB.get());
            return limitDB.get();
        }
        else {
            limitRepository.save(limitToSave);
            return limitToSave;
        }
    }
}
