package com.apicontrole.demo.infraestructure.repository;

import com.apicontrole.demo.infraestructure.entitys.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expenses,Integer> {


}