package com.apicontrole.demo.service.impl;

import com.apicontrole.demo.dto.expenses.ExpenseCreateDTO;
import com.apicontrole.demo.infraestructure.entitys.Budget;
import com.apicontrole.demo.infraestructure.entitys.Expenses;
import com.apicontrole.demo.infraestructure.repository.BudgetRepository;
import com.apicontrole.demo.infraestructure.repository.ExpenseRepository;
import com.apicontrole.demo.service.ExpenseService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;


    public  ExpenseServiceImpl (ExpenseRepository expenseRepository,BudgetRepository budgetRepository){
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }


    @Override
    public ExpenseCreateDTO.ExpenseResponse createExpense(ExpenseCreateDTO dto, Integer id){
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));

        Expenses expense = Expenses.builder()
                .nome(dto.getNome())
                .valor(dto.getValor())
                .categoria(dto.getCategoria())
                .budget(budget)
                .build();
        Expenses saved = expenseRepository.save(expense);

        return new ExpenseCreateDTO.ExpenseResponse(saved.getId(),saved.getNome(),saved.getCategoria(), saved.getValor());


    }



}
