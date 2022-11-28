package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

public class Notificacoes implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private Date data;

    @Column(name = "typeNot")
    @Enumerated(EnumType.ORDINAL)
    private TypeNotfication type;

    @Column(name = "descricao")
    private String descricao;

    //colocar com referencia ao user
}
