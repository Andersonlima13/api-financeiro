package com.apicontrole.demo.service.impl;

import com.apicontrole.demo.config.JwtUtil;
import com.apicontrole.demo.dto.UserCreateDTO;
import com.apicontrole.demo.dto.UserResponseDTO;
import com.apicontrole.demo.exceptionHandler.EmailAlreadyExistsException;
import com.apicontrole.demo.infraestructure.entitys.User;
import com.apicontrole.demo.infraestructure.repository.UserRepository;
import com.apicontrole.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

    }

    @Override
    public UserResponseDTO createUser(UserCreateDTO dto){
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email já está em uso!");
        }

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

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

   public User getUser(Integer id){
        return userRepository.findById(id).orElseThrow( () -> new RuntimeException("usuario nao encontrado"));
   }







}
