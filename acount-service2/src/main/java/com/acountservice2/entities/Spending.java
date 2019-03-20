package com.acountservice2.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "spending")
@Data
@NoArgsConstructor
public class Spending {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Date spendingtime;
    @Column(name = "user_id")
    private Long userId;

    public Spending(double amount, Category category, Date spendingtime, Long userId) {
        this.amount = amount;
        this.category = category;
        this.spendingtime = spendingtime;
        this.userId = userId;
    }




}
