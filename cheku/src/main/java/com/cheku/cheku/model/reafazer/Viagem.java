package com.cheku.cheku.model.reafazer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;


@Entity
@Table(name = "viagem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicio")
    private Date data_inicio;

    @Column(name = "data_fim")
    private Date data_fim;

    // @Column(name = "carro")
    // @OneToOne
    // private Car carro;

    // @Column(name = "points")
    // @OneToMany
    // private List<Localization> points;

}
