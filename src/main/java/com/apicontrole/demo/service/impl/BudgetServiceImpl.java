package com.apicontrole.demo.service.impl;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;
import com.apicontrole.demo.infraestructure.entitys.Budget;
import com.apicontrole.demo.infraestructure.entitys.User;
import com.apicontrole.demo.infraestructure.repository.BudgetRepository;
import com.apicontrole.demo.infraestructure.repository.UserRepository;
import com.apicontrole.demo.service.BudgetService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;


    public  BudgetServiceImpl(BudgetRepository budgetRepository, UserRepository userRepository){
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;

    }


    @Override
    public BudgetCreateDTO.BudgetResponse createBudget(@NotNull BudgetCreateDTO dto, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("usuario não encontrado"));


        Budget budget = Budget.builder()
                .limite(dto.getLimite())
                .nome(dto.getNome())
                .valor_gasto(0.0)
                .user(user)
                .build();

        Budget saved = budgetRepository.save(budget);

        return new BudgetCreateDTO.BudgetResponse(saved.getId(),saved.getLimite(),saved.getNome());


    }

    @Override
    public List<Budget> getAllBudgets(){
        return budgetRepository.findAll();
    }

    @Override
    public Budget getBudgetById(Integer id){
        return budgetRepository.findById(id).orElseThrow( () -> new RuntimeException("Orçamento não encontrado"));
    }

    @Override
    public Budget deleteBudgetById(Integer id){
        Budget budget = budgetRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Orçamento não encontrado"));
        budgetRepository.deleteById(id);
        return budget;
    }

    @Override
    public Budget updateBudgetById(Integer id, @NotNull BudgetCreateDTO dto) {
        Budget updatedBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orcamento não encontrado"));
        updatedBudget.setLimite(dto.getLimite());
        updatedBudget.setNome(dto.getNome());

        return budgetRepository.save(updatedBudget);



    }


}
