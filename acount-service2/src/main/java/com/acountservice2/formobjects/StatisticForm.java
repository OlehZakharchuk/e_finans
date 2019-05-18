package com.acountservice2.formobjects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticForm {

    private Short month;//0 means that gonna set year's limit
    private Boolean isYear;
    private Short year;
}
