package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.expenses.ExpenseCreateDTO;

public interface ExpenseService {
    ExpenseCreateDTO.ExpenseResponse createExpense(ExpenseCreateDTO dto, Integer id);
}
