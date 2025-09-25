package com.apicontrole.demo.controller;

import com.apicontrole.demo.infraestructure.entitys.User;
import com.apicontrole.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Search user info by id", description = "Should returns user infos if exists")
    @ApiResponse(responseCode = "200", description = "User returns")
    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Integer id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }





}
