package com.apicontrole.demo.infraestructure.entitys;

import com.google.auto.value.AutoValue;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AutoValue.Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column
    private Integer accounts;

    @Column
    private Integer saldo;
}
