package com.acountservice2.formobjects;

import com.acountservice2.entities.Limit;
import com.acountservice2.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class LimitForm {

    @DecimalMin(value = "0.00", message = "minimal value is 0")
    @Digits(message = "Two digits after point", fraction = 2, integer = 10)
    private double amount;
    private Short month;//0 means that gonna set year's limit
    private Boolean isYear;
    private Short year;

    private Money money;
    public Limit toLimit(long userId){
        if(isYear)
            return new Limit(amount*money.multiplier, (short)0, year,userId);
        else
            return new Limit(amount*money.multiplier, month, year,userId);



    }


}
