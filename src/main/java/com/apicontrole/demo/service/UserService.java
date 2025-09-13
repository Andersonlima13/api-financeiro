package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.UserCreateDTO;
import com.apicontrole.demo.dto.UserResponseDTO;

// contrato (oque o serviço deve fazer )



public interface UserService {
    UserResponseDTO createUser(UserCreateDTO dto);
}