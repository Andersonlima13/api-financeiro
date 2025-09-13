package com.apicontrole.demo.controller;


import com.apicontrole.demo.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUsuario(@RequestBody Usuario usuario) {
        boolean loginValido = firebaseService.verificarUsuario(usuario.getEmail(), usuario.getPassword());

        Map<String, String> response = new HashMap<>();

        if (loginValido) {
            response.put("message", "Login bem-sucedido!");
            return ResponseEntity.ok(response); // Resposta com sucesso no login
        } else {
            response.put("message", "Credenciais inválidas!");
            return ResponseEntity.status(401).body(response); // Erro caso as credenciais sejam inválidas
        }
    }



}
