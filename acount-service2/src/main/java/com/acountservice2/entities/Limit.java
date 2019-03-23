package com.acountservice2.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "limits")
@Data
@NoArgsConstructor
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private Short month;
    private Short year;
    @Column(name = "user_id")
    private Long userId;

    public Limit(double amount, short month, short year, Long userId) {
        this.amount = amount;
        this.month = month;
        this.year = year;
        this.userId = userId;
    }
}
