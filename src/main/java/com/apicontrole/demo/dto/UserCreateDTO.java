package com.apicontrole.demo.dto;

import lombok.Data;

// aqui , colocamos oque vamos passar via front end
@Data
public class UserCreateDTO {
    private String nome;
    private String email;
    private String password;

}
