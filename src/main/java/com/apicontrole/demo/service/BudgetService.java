package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;
import com.apicontrole.demo.infraestructure.entitys.Budget;

import java.util.List;

public interface BudgetService {
    List<Budget> getAllBudgets();
    BudgetCreateDTO.BudgetResponse createBudget(BudgetCreateDTO dto, String email);
}
