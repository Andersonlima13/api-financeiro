package com.apicontrole.demo.service;

import com.apicontrole.demo.dto.UserCreateDTO;
import com.apicontrole.demo.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO dto);
}