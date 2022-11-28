package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cars")

public class Car implements Serializable {

    @column(name = "id")
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @column(name = "marca", nullable = false)
    private String marca;

    @column(name = "modelo", nullable = false)
    private String modelo;

    @column(name = "ano", nullable = false)
    private int ano;

    @column(name = "matricula", unique = true, nullable = false)
    private String matricula;

    @column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TypeCar type;

    @OneToOne(optional = false)
    @JoinColumn(name = "motor_id", referencedColumnName = "id", nullable = false)
    private Motor motor;

    @OneToMany
    @JoinColumn(name = "pneu_id", referencedColumnName = "id")
    private List<Pneus> pneus;

    @OneToMany
    @JoinColumn(name = "viagem_id", referencedColumnName = "id")
    private List<Viagem> viagens;

    @column(name = "localization")
    @OneToOne
    private Localization localization;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "nif", nullable = false)
    private User user;

    //alteraveis
    @column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusCar status;

    @column(name = "km")
    private int km;

    @column(name = "velocidade")
    private int velocidade;

//    @column(name = "caixa_velocidades")
//    private int caixa_velocidades;


    @column(name = "ligth")
    @Enumerated(EnumType.ORDINAL)
    private StatusLigth ligth;




}


