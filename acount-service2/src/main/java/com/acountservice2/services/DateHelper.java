package com.acountservice2.services;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
@Service
public class DateHelper {

    public void normalizeTimeMembers(Calendar date){
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MINUTE,0);
        date.set(Calendar.HOUR_OF_DAY, 0);
    }
}
