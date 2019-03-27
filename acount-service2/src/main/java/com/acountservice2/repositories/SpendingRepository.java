package com.acountservice2.repositories;


import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpendingRepository extends PagingAndSortingRepository<Spending, Long> {
    List<Spending> findByAmount(double amount);

    List<Spending> findByUserId(long userId);
    List<Spending> findAllByUserId(long userId, Pageable pageable);

}
