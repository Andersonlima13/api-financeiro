package com.apicontrole.demo.controller;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;

import com.apicontrole.demo.infraestructure.entitys.Budget;
import com.apicontrole.demo.service.BudgetService;
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

    @PostMapping("/create")
    public ResponseEntity<BudgetCreateDTO.BudgetResponse> createBudget(@RequestBody BudgetCreateDTO dto, @NotNull Authentication authentication) {
        String email = authentication.getName();
        BudgetCreateDTO.BudgetResponse response = budgetService.createBudget(dto,email);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<BudgetCreateDTO.BudgetResponse>>getAllBudgets(){
        List<BudgetCreateDTO.BudgetResponse> budgets = budgetService.getAllBudgets()
        .stream().map( budget -> new BudgetCreateDTO.BudgetResponse
                        (budget.getId(), budget.getLimite(), budget.getNome())).toList();

        return ResponseEntity.ok(budgets);

    }

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

    @PutMapping("/update/{id}")
    public ResponseEntity<BudgetCreateDTO.BudgetResponse>updateBudgetById(@PathVariable Integer id, @RequestBody BudgetCreateDTO dto){
        Budget updatedBudget = budgetService.updateBudgetById(id,dto);

        BudgetCreateDTO.BudgetResponse response = new BudgetCreateDTO.BudgetResponse(updatedBudget.getId(), updatedBudget.getLimite(), updatedBudget.getNome());

        return ResponseEntity.ok(response);

    }





}
