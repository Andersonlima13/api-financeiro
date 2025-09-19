package com.apicontrole.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/server") // Define a rota GET /api/hello
    public Init server() {
        return new Init("Rota api/server");

    }



}
