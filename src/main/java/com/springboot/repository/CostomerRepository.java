package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.model.Costomer;


@Repository
public interface CostomerRepository extends JpaRepository<Costomer, Long> {

}
