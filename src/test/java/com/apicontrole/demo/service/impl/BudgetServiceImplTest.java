package com.apicontrole.demo.service.impl;

import com.apicontrole.demo.dto.budget.BudgetCreateDTO;
import com.apicontrole.demo.infraestructure.entitys.Budget;
import com.apicontrole.demo.infraestructure.entitys.User;
import com.apicontrole.demo.infraestructure.repository.BudgetRepository;
import com.apicontrole.demo.infraestructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BudgetServiceImplTest {

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BudgetServiceImpl budgetService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1)
                .email("test@example.com")
                .password("123456")
                .build();
    }

    @Test
    void shouldCreateBudget() {
        // Arrange
        BudgetCreateDTO dto = new BudgetCreateDTO();
        dto.setNome("Alimentação");
        dto.setLimite(1000.0);

        Budget budget = Budget.builder()
                .id(1)
                .nome(dto.getNome())
                .limite(dto.getLimite())
                .valor_gasto(0.0)
                .user(user)
                .build();

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(budgetRepository.save(any(Budget.class))).thenReturn(budget);

        // Act
        BudgetCreateDTO.BudgetResponse response = budgetService.createBudget(dto, "test@example.com");

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals("Alimentação", response.getNome());
        assertEquals(1000.0, response.getLimite());

        verify(userRepository, times(1)).findByEmail("test@example.com");
        verify(budgetRepository, times(1)).save(any(Budget.class));
    }

    @Test
    void exceptionIfUserNotFound() {
        // Arrange
        BudgetCreateDTO dto = new BudgetCreateDTO();
        dto.setNome("Transporte");
        dto.setLimite(500.0);

        when(userRepository.findByEmail("inexistente@example.com")).thenReturn(Optional.empty());

        // Act + Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> budgetService.createBudget(dto, "inexistente@example.com"));

        assertEquals("usuario não encontrado", exception.getMessage());
        verify(budgetRepository, never()).save(any());
    }


    @Test
    void exceptionIfBudgetNomeIsNull() {
        // Arrange
        BudgetCreateDTO dto = new BudgetCreateDTO();
        dto.setNome(""); // nome inválido
        dto.setLimite(1000.0);

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> budgetService.createBudget(dto, "test@example.com"));

        assertEquals("Nome do budget não pode ser vazio", exception.getMessage());

        verify(budgetRepository, never()).save(any(Budget.class));
    }

}
