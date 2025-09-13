package com.apicontrole.demo.service.impl;

import com.apicontrole.demo.dto.UserCreateDTO;
import com.apicontrole.demo.dto.UserResponseDTO;
import com.apicontrole.demo.infraestructure.entitys.User;
import com.apicontrole.demo.infraestructure.repository.UserRepository;
import com.apicontrole.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO createUser(UserCreateDTO dto){
        User user = User.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .accounts(0)
                .saldo(0)
                .build();

        User saved = userRepository.save(user);

        return  new UserResponseDTO(saved.getId(), saved.getNome(),saved.getEmail());

    }





}
