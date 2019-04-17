package com.acountservice2.formobjects;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import com.acountservice2.services.DateHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@Data
@NoArgsConstructor
public class SpendingForm {
    @Autowired
    DateHelper dateHelper;
    @Digits(integer = 10, fraction = 2, message = "should be digits here max 10 digits")
    private double amount;
    private Date timePlacedSpending;
    private Category category;
    private Money money;

    public Spending toSpending(long userId){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timePlacedSpending);
        //problem, when adding to DB, mysql substact 1 from day field
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        timePlacedSpending = new Date(calendar.getTimeInMillis());

        return new Spending(amount*money.multiplier, category, timePlacedSpending, userId);
    }

}
