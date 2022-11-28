package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cars")
public class Car  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "ano", nullable = false)
    private int ano;

    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;

    @Column(name = "type", nullable = false)
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

    @Column(name = "localization")
    @OneToOne
    private Localization localization;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "nif", nullable = false)
    private User user;

    //alteraveis
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusCar status;

    @Column(name = "km")
    private int km;

    @Column(name = "velocidade")
    private int velocidade;

    //-1 seria REVERSE
    //0 seria NONE
    @column(name = "caixa_velocidades")
    private int caixa_velocidades;

    //niveis de combustivel, agua e  oleo
    //dados entre 0 e 100
    @Column(name = "combustivel")
    private int combustivel;

    @Column(name = "agua")
    private int agua;

    @Column(name = "oleo")
    private int oleo;

    @Column(name = "ligth")
    @Enumerated(EnumType.ORDINAL)
    private StatusLigth ligth;




}


