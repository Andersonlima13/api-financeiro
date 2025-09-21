package com.apicontrole.demo.infraestructure.repository;


import com.apicontrole.demo.infraestructure.entitys.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget,Integer> {
}
