package com.acountservice2.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "category")
public class Category  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", length = 200)
    private String description;
    @Column(length = 45)
    private String name;
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
 //   private List<Spending> spendings= new ArrayList<>();




}
