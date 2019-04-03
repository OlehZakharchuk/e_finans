package com.acountservice2.services;

import com.acountservice2.entities.Limit;
import com.acountservice2.entities.User;
import com.acountservice2.repositories.LimitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
@Service
@Slf4j
public class LimitServiceSD implements LimitService {

    @Autowired
    LimitRepository limitRepository;
    @Override
    public Limit update(Limit limitToSave) {
        Optional<Limit> limitDB = limitRepository.getByMonthAndYear(limitToSave.getMonth(), limitToSave.getYear());
        if(limitDB.isPresent()){
            log.info("Limit already is: "+limitDB.get());
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

    @Override
    public double getYearLimit(User user) {
        Optional<Limit> yearLimitCurentUser = limitRepository.getByUserIdAndMonthAndYear(user.getId(),(short)0,
                (short) Calendar.getInstance().get(Calendar.YEAR));
        return yearLimitCurentUser.isPresent() ? yearLimitCurentUser.get().getAmount() : 0;
    }

    @Override
    public double getMonthLimit(User user) {

        Optional<Limit> monthLimitCurentUser = limitRepository.getByUserIdAndMonthAndYear(user.getId(),
                (short)(Calendar.getInstance().get(Calendar.MONTH)+1),
                (short) Calendar.getInstance().get(Calendar.YEAR) );
        return monthLimitCurentUser.isPresent() ? monthLimitCurentUser.get().getAmount() : 0;
    }
}
