package com.acountservice2.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", length = 200)
    private String description;
    @Column(length = 45)
    private String name;
    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL)
    private Set<Spending> spendings;
}
