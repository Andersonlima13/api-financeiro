package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.expenses.ExpenseCreateDTO;
import com.apicontrole.demo.infraestructure.entitys.Expenses;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ExpenseService {
    ExpenseCreateDTO.ExpenseResponse createExpense(ExpenseCreateDTO dto, Integer id);
    List<Expenses> getAllExpenses();
    Expenses getExpenseById(Integer id);
    Expenses deleteExpenseById(Integer id);

    Expenses updateExpenseById(Integer id, @NotNull ExpenseCreateDTO dto);
}
