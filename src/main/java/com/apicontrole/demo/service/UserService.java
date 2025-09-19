package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.user.LoginDTO;
import com.apicontrole.demo.dto.user.UserCreateDTO;
import com.apicontrole.demo.infraestructure.entitys.User;

// contrato (oque o serviço deve fazer )



public interface UserService {
    LoginDTO.UserResponseDTO createUser(UserCreateDTO dto);
    String login(String email, String password);
    User getUser(Integer id);
}