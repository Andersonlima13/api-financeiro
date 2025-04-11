package com.apicontrole.demo.model;

public class Usuario {

    private String name; 
    private String email;
    private String password;

    // Construtor
    public Usuario(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters e Setters
    public String getNome() { 
        return name;
    }

    public void setNome(String nome) { 
        this.name = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
