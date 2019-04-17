package com.acountservice2.services;

import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SpendingService {

    List<Spending> getLastNSpendingOfCurrentUser(long userId);
    double getMonthSpending(User user);
    double getYearSpending(User user);
    double getCurrentDaySpending(User user);
    List<Spending> getSpendingForMonthOfYear(int month, int year, User user);
}
