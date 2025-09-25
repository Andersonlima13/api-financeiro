package com.apicontrole.demo.controller;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;

import com.apicontrole.demo.infraestructure.entitys.Budget;
import com.apicontrole.demo.service.BudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @Operation(summary = "Create budget", description = "Should return created budget")
    @ApiResponse(responseCode = "200", description = "Budget was been created")
    @PostMapping("/create")
    public ResponseEntity<BudgetCreateDTO.BudgetResponse> createBudget(@RequestBody BudgetCreateDTO dto, @NotNull Authentication authentication) {
        String email = authentication.getName();
        BudgetCreateDTO.BudgetResponse response = budgetService.createBudget(dto,email);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return budgets", description = "Should return all budgets from logged user")
    @ApiResponse(responseCode = "200", description = "List of budgets returned")
    @GetMapping("/all")
    public ResponseEntity<List<BudgetCreateDTO.BudgetResponse>>getAllBudgets(){
        List<BudgetCreateDTO.BudgetResponse> budgets = budgetService.getAllBudgets()
        .stream().map( budget -> new BudgetCreateDTO.BudgetResponse
                        (budget.getId(), budget.getLimite(), budget.getNome())).toList();

        return ResponseEntity.ok(budgets);

    }

    @Operation(summary = "Return budget by id", description = "Should return budget if exists")
    @ApiResponse(responseCode = "200", description = "Budget returns")
    @GetMapping("/{id}")
    public ResponseEntity<BudgetCreateDTO.BudgetResponse>getBudgetById(@PathVariable Integer id){
        Budget budget  = budgetService.getBudgetById(id);

        BudgetCreateDTO.BudgetResponse budgetResponse = new BudgetCreateDTO.BudgetResponse(
                budget.getId(),
                budget.getLimite(),
                budget.getNome()
        );
        return ResponseEntity.ok(budgetResponse);
    }


    @Operation(summary = "Delete budget", description = "Should delete budget")
    @ApiResponse(responseCode = "200", description = "Budget was been deleted")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BudgetCreateDTO.BudgetResponse>deleteBudgetById(@PathVariable Integer id){
        Budget budget = budgetService.deleteBudgetById(id);

        BudgetCreateDTO.BudgetResponse budgetResponse = new BudgetCreateDTO.BudgetResponse(
                budget.getId(),
                budget.getLimite(),
                budget.getNome()
        );
        return ResponseEntity.ok(budgetResponse);
    }


    @Operation(summary = "Updated budget by id", description = "Should update user budget")
    @ApiResponse(responseCode = "200", description = "Budget was been updated")
    @PutMapping("/update/{id}")
    public ResponseEntity<BudgetCreateDTO.BudgetResponse>updateBudgetById(@PathVariable Integer id, @RequestBody BudgetCreateDTO dto){
        Budget updatedBudget = budgetService.updateBudgetById(id,dto);

        BudgetCreateDTO.BudgetResponse response = new BudgetCreateDTO.BudgetResponse(updatedBudget.getId(), updatedBudget.getLimite(), updatedBudget.getNome());

        return ResponseEntity.ok(response);

    }





}
