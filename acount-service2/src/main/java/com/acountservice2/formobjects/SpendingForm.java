package com.acountservice2.formobjects;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class SpendingForm {

    private double amount;
    private Date timePlacedSpending;
    private Category category;

    private Money money;

    public Spending toSpending(long userId){
        return new Spending(amount*money.multiplier, category, timePlacedSpending, userId);
    }

}
