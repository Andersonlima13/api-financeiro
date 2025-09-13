package com.apicontrole.demo.controller;

import com.apicontrole.demo.infraestructure.entitys.User;
import com.apicontrole.demo.infraestructure.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para criar usuário
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> criarUser(@RequestBody User user) {
        System.out.println("Recebendo usuário: " + user.getNome() + ", " + user.getEmail());

        Map<String, String> response = new HashMap<>();
        try {
            userRepository.save(user);
            response.put("message", "Usuário criado com sucesso!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Erro ao criar usuário: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
