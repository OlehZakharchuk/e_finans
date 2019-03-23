package com.acountservice2.repositories;

import com.acountservice2.entities.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends CrudRepository<Limit, Long> {
}
