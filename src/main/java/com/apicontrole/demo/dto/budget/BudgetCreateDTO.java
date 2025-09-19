package com.apicontrole.demo.dto.budget;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
public class BudgetCreateDTO {
    private Double limite;
    private String nome;


    @Data
    @AllArgsConstructor
    public static class BudgetResponse {
        private Integer id;
        private Double limite;
        private String nome;


    }
}
