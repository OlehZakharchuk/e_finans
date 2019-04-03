package com.acountservice2.services;

import com.acountservice2.entities.Limit;
import com.acountservice2.entities.User;

public interface LimitService {

    Limit update(Limit limit);
    double getYearLimit(User user);
    double getMonthLimit(User user);
}
