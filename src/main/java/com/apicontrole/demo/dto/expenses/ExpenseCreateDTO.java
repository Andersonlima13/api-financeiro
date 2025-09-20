package com.apicontrole.demo.dto.expenses;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ExpenseCreateDTO {
    private String nome;
    private String categoria;
    private Double Valor;


    @Data
    @AllArgsConstructor
    public static class ExpenseResponse {
        private Integer id;
        private String nome;
        private String categoria;
        private Double Valor;

    }


}
