package com.acountservice2.services;

import com.acountservice2.config.AccountProps;
import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.repositories.SpendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class SpendingServiceWithRepo implements SpendingService {
    @Autowired
    SpendingRepository spendingRepository;
    @Autowired
    AccountProps accountProps;
    @Autowired
    DateHelper dateHelper;
    @Override
    public List<Spending> getLastNSpendingOfCurrentUser(long userId) {
        Pageable pageable = PageRequest.of(0, accountProps.getAmountOfSpending(),
                Sort.by("spendingtime").descending());
        return spendingRepository.findAllByUserId(userId, pageable);
    }


    @Override
    public double getMonthSpending(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date from  = new Date(calendar.getTimeInMillis());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date to = new Date(calendar.getTimeInMillis());
        double sum=0;
        sum = spendingRepository.findByUserIdAndSpendingtimeBetween(user.getId(), from, to).stream().
                mapToDouble(spending -> spending.getAmount()).sum();
        return sum;
    }

    @Override
    public double getYearSpending(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date from = new Date(calendar.getTimeInMillis());
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date to = new Date(calendar.getTimeInMillis());
        double sum = spendingRepository.findByUserIdAndSpendingtimeBetween(user.getId(), from, to)
                .stream().mapToDouble(Spending::getAmount).sum();
        return sum;
    }

    @Override
    public double getCurrentDaySpending(User user) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        return spendingRepository.findAllByUserIdAndSpendingtime(user.getId(), date)
                .stream().mapToDouble(Spending::getAmount).sum();
    }

    @Override
    public List<Spending> getSpendingForMonthOfYear(int month, int year, User user){
        month-=1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        dateHelper.normalizeTimeMembers(calendar);
        Date from = new Date(calendar.getTimeInMillis());
        calendar.set(year, month, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date to = new Date(calendar.getTimeInMillis());
        return spendingRepository.findByUserIdAndSpendingtimeBetween(user.getId(), from, to);
    }
}
