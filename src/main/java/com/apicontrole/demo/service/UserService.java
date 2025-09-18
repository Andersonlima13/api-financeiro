package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.UserCreateDTO;
import com.apicontrole.demo.dto.UserResponseDTO;
import com.apicontrole.demo.infraestructure.entitys.User;

// contrato (oque o serviço deve fazer )



public interface UserService {
    UserResponseDTO createUser(UserCreateDTO dto);
    String login(String email, String password);
    User getUser(Integer id);

}