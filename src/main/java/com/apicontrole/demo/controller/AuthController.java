package com.apicontrole.demo.controller;

import com.apicontrole.demo.dto.user.LoginDTO;
import com.apicontrole.demo.dto.user.UserCreateDTO;
import com.apicontrole.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Create user", description = "Should return created user")
    @ApiResponse(responseCode = "200", description = "User was been created")
    @PostMapping("/create")
    public ResponseEntity<LoginDTO.UserResponseDTO> createUser(@RequestBody UserCreateDTO dto) {
        LoginDTO.UserResponseDTO response = userService.createUser(dto);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Authentication", description = "Should authenticate if user exists")
    @ApiResponse(responseCode = "200", description = "Login successful!")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO dto) {
        String token = userService.login(dto.getEmail(), dto.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }



}
