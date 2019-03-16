package com.acountservice2.repositories;


import com.acountservice2.entities.Spending;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpendingRepository extends CrudRepository<Spending, Long> {
    List<Spending> findByAmount(double amount);
}
