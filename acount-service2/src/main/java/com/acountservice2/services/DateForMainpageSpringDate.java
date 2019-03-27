package com.acountservice2.services;

import com.acountservice2.config.AccountProps;
import com.acountservice2.entities.Spending;
import com.acountservice2.repositories.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateForMainpageSpringDate implements DateForMainPageInterface{
    @Autowired
    SpendingRepository spendingRepository;
    @Autowired
    AccountProps accountProps;
    @Override
    public List<Spending> getLastNSpendingOfCurrentUser(long userId) {
        Pageable pageable = PageRequest.of(0, accountProps.getAmountOfRecords(),
                Sort.by("spendingtime").descending());
        return spendingRepository.findAllByUserId(userId, pageable);
    }
}
