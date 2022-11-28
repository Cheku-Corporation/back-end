package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "pneus")
@AllArgsConstructor
@NoArgsConstructor
public class Pneus implements Serializable{

    //dados estaticos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "date")
    private Date date;

    //dados dinamicos
//    @Column(name = "pressao", nullable = false)
//    private int pressao;

//    @Column(name = "status")
//    @Enumerated(EnumType.ORDINAL)
//    private StatusPneus status;


}
