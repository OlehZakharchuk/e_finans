package com.acountservice2.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "spending")
public class Spending {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    private Date spendingtime;

    @ManyToMany(mappedBy = "spendings")
    Set<User> users;
    public Spending(Long userId, double amount, Category categoryId, Date spendingtime) {
        this.userId = userId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.spendingtime = spendingtime;
    }
}
