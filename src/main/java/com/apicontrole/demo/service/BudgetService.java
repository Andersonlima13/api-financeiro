package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;

public interface BudgetService {


    BudgetCreateDTO.BudgetResponse createBudget(BudgetCreateDTO dto, String email);
}
