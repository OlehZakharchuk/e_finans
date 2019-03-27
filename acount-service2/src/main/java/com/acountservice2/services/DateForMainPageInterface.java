package com.acountservice2.services;

import com.acountservice2.entities.Spending;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DateForMainPageInterface {

    public List<Spending> getLastNSpendingOfCurrentUser(long userId);
}
