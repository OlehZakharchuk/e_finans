package com.acountservice2.formobjects;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@NoArgsConstructor
public class SpendingForm {
    @Digits(integer = 10, fraction = 2, message = "should be digits here max 10 digits")
    private double amount;
    private Date timePlacedSpending;
    private Category category;
    private Money money;

    public Spending toSpending(long userId){
        return new Spending(amount*money.multiplier, category, timePlacedSpending, userId);
    }

}
