package com.apicontrole.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello") // Define a rota GET /api/hello
    public Saudacao hello() {
        return new Saudacao("Olá, API funcionando!", "Teste");

    }



}
