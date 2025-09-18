package com.apicontrole.demo.controller;

import com.apicontrole.demo.dto.LoginDTO;
import com.apicontrole.demo.dto.UserCreateDTO;
import com.apicontrole.demo.dto.UserResponseDTO;
import com.apicontrole.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Método para criar usuário
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto) {
        UserResponseDTO response = userService.createUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO dto) {
        String token = userService.login(dto.getEmail(), dto.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }



}
