package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "motors")
public class Motor implements Serializable {

    @column(name = "id")
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @column(name = "potencia")
    private int potencia;

    @column(name = "cilindrada")
    private int cilindrada;

    @column(name = "modelo")
    private String modelo;

    //alteraveis
    @column(name = "tempertatura")
    private int temperatura;

    @column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusMotor status;

}
