package com.apicontrole.demo.controller;

import com.apicontrole.demo.dto.expenses.ExpenseCreateDTO;
import com.apicontrole.demo.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


///  esse caminho precisa mudar , pois expense é relativo ao id de uma categoria (budget)
@RestController
@RequestMapping("/budgets/{budgetId}/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseCreateDTO.ExpenseResponse> createExpense(@PathVariable("budgetId") Integer id , @RequestBody ExpenseCreateDTO dto){
        ExpenseCreateDTO.ExpenseResponse expense = expenseService.createExpense(dto,id);
        return ResponseEntity.ok(expense);


    }





}
