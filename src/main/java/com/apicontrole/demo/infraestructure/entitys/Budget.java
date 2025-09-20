package com.apicontrole.demo.infraestructure.entitys;

import com.google.auto.value.AutoValue;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AutoValue.Builder
@Entity
@Builder
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double limite;


    @ManyToOne(fetch = FetchType.LAZY) // muitos budgets podem pertencer a 1 usuário
    @JoinColumn(name = "user_id", nullable = false) // cria a coluna user_id na tabela budget
    private User user;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double valor_gasto;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expenses> expenses = new ArrayList<>();

}