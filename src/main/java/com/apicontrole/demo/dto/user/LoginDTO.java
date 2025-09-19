package com.apicontrole.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;

    @Data
    @AllArgsConstructor
    public static class UserResponseDTO {
        private Integer id;
        private String nome;
        private String email;

    }
}