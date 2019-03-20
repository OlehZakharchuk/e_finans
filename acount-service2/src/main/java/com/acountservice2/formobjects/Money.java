package com.acountservice2.formobjects;

public enum Money{
        USD(1.2), PLN(1), EUR(1.3);
        double multiplier;
        Money(double coef){
            multiplier=coef;
        }
    }