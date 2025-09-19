package com.apicontrole.demo.controller;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;

import com.apicontrole.demo.service.BudgetService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/create")
    public ResponseEntity<BudgetCreateDTO.BudgetResponse> createBudget(@RequestBody BudgetCreateDTO dto, Authentication authentication) {
        String email = authentication.getName();
        BudgetCreateDTO.BudgetResponse response = budgetService.createBudget(dto,email);
        return ResponseEntity.ok(response);
    }

}
