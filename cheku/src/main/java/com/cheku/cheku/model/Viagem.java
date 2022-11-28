package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;


@Entity
@Table(name = "viagem")
@Data
public class Viagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicio")
    private Date data_inicio;

    @Column(name = "data_fim")
    private Date data_fim;

    @Column(name = "distancia")
    private int distancia;

    @Column(name = "carro")
    @OneToOne
    private Car carro;

    @Column(name = "points")
    @OneToMany
    private List<Localization> points;

}