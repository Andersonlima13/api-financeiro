package com.apicontrole.demo.controller;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;
import com.apicontrole.demo.dto.expenses.ExpenseCreateDTO;
import com.apicontrole.demo.infraestructure.entitys.Budget;
import com.apicontrole.demo.infraestructure.entitys.Expenses;
import com.apicontrole.demo.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


///  esse caminho precisa mudar , pois expense é relativo ao id de uma categoria (budget)
@RestController
@RequestMapping("/budgets/{budgetId}")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @Operation(summary = "Create Expense", description = "Should return created Expense")
    @ApiResponse(responseCode = "200", description = "Expense was been created")
    @PostMapping("/create")
    public ResponseEntity<ExpenseCreateDTO.ExpenseResponse> createExpense(@PathVariable("budgetId") Integer id , @RequestBody ExpenseCreateDTO dto){
        ExpenseCreateDTO.ExpenseResponse expense = expenseService.createExpense(dto,id);
        return ResponseEntity.ok(expense);

    }

    @Operation(summary = "Return expenses", description = "Should return all expenses from budget")
    @ApiResponse(responseCode = "200", description = "List of expenses returned")
    @GetMapping("/all")
    public ResponseEntity<List<ExpenseCreateDTO.ExpenseResponse>>getAllExpenses(){
        List<ExpenseCreateDTO.ExpenseResponse> expenses = (List<ExpenseCreateDTO.ExpenseResponse>) expenseService.getAllExpenses()
                .stream().map(expense -> new ExpenseCreateDTO.ExpenseResponse(
                        expense.getId(), expense.getCategoria(), expense.getNome(), expense.getValor()
                )).toList();
        return ResponseEntity.ok(expenses);

    }


    @Operation(summary = "Return expense of budget by id", description = "Should return expenses if exists")
    @ApiResponse(responseCode = "200", description = "Expense returns")
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseCreateDTO.ExpenseResponse>getExpenseById(@PathVariable Integer id){
        Expenses expenses  = expenseService.getExpenseById(id);

        ExpenseCreateDTO.ExpenseResponse expenseResponse = new ExpenseCreateDTO.ExpenseResponse(
                expenses.getId(),
                expenses.getNome(),
                expenses.getCategoria(),
                expenses.getValor()
        );
        return ResponseEntity.ok(expenseResponse);
    }


    @Operation(summary = "Delete expense", description = "Should delete expense by id")
    @ApiResponse(responseCode = "200", description = "Expense was been deleted")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ExpenseCreateDTO.ExpenseResponse>deleteExpenseById(@PathVariable Integer id){
        Expenses expenses = expenseService.deleteExpenseById(id);

        ExpenseCreateDTO.ExpenseResponse expenseResponse = new ExpenseCreateDTO.ExpenseResponse(
                expenses.getId(),
                expenses.getNome(),
                expenses.getCategoria(),
                expenses.getValor()
        );
        return ResponseEntity.ok(expenseResponse);
    }


    @Operation(summary = "Delete expense by id", description = "Should delete expense by id from budget")
    @ApiResponse(responseCode = "200", description = "Expense was been deleted")
    @PutMapping("/{id}/update")
    public ResponseEntity<ExpenseCreateDTO.ExpenseResponse>updateExpenseById(@PathVariable Integer id, @RequestBody ExpenseCreateDTO dto){
        Expenses updatedExpense = expenseService.updateExpenseById(id,dto);
        ExpenseCreateDTO.ExpenseResponse response = new ExpenseCreateDTO.ExpenseResponse(
                updatedExpense.getId(),
                updatedExpense.getNome(),
                updatedExpense.getCategoria(),
                updatedExpense.getValor());
        return ResponseEntity.ok(response);
    }




















}
