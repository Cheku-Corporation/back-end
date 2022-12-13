package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "motors")
@AllArgsConstructor
@NoArgsConstructor
public class Motor {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Potencia is required")
    @Column(name = "potencia", nullable = false)
    private int potencia;

    @NotNull(message = "Cilindrada is required")
    @Column(name = "cilindrada", nullable = false)
    private int cilindrada;

    @NotNull(message = "Modelo is required")
    @Column(name = "modelo", nullable = false)
    private String modelo;
}
