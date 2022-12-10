package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    //Atributos estaticos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 8, max = 8)
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "dateofinspection", nullable = true)
    private Date dateofinspection;

    @Column(name = "dateofinsurance", nullable = true)
    private Date dateofinsurance;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group ;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel model;
}