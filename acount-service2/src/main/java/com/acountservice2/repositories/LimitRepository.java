package com.acountservice2.repositories;

import com.acountservice2.entities.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepository extends PagingAndSortingRepository<Limit, Long> {

    Optional<Limit> getByMonthAndYear(short month, short year);

    Optional<Limit> getByUserIdAndMonthAndYear(long userid, short month, short year);
}
