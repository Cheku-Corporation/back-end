package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car  {

    //Atributos estaticos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;

    //Relacionamentos (verificar)
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TypeCar type;

    @OneToOne(optional = false)
    @JoinColumn(name = "motor_id", referencedColumnName = "id", nullable = false)
    private Motor motor;

    @OneToOne(optional = false)
    @JoinColumn(name = "pneu_id", referencedColumnName = "id")
    private Pneus pneus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private User user;

    //alteraveis
//    @Column(name = "status")
//    @Enumerated(EnumType.ORDINAL)
//    private StatusCar status;
//
//    @Column(name = "km")
//    private int km;
//
//    //niveis de combustivel, agua e  oleo
//    //dados entre 0 e 100
//    @Column(name = "combustivel")
//    private int combustivel;
//
//    @Column(name = "agua")
//    private int agua;
//
//    @Column(name = "oleo")
//    private int oleo;
//
//    @Column(name = "ligth")
//    @Enumerated(EnumType.ORDINAL)
//    private StatusLigth ligth;




}


