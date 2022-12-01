package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "potencia", nullable = false)
    private int potencia;

    @Column(name = "cilindrada", nullable = false)
    private int cilindrada;

    @Column(name = "modelo", nullable = false)
    private String modelo;
}
