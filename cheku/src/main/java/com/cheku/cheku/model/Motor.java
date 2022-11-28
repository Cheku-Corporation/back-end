package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "motors")
@AllArgsConstructor
@NoArgsConstructor
public class Motor implements Serializable {

    //dados estaticos
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "potencia")
    private int potencia;

    @Column(name = "cilindrada")
    private int cilindrada;

    @Column(name = "modelo")
    private String modelo;

    //dados dinamicos
//    @Column(name = "tempertatura")
//    private int temperatura;
//
//    @Column(name = "status")
//    @Enumerated(EnumType.ORDINAL)
//    private StatusMotor status;

}
