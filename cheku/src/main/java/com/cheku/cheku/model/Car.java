package com.cheku.cheku.model;

import com.cheku.cheku.model.enums.TypeCar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCar type;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "motor")
    private Motor motor;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "pneus")
    private Pneus pneus;
//
//    @ManyToOne(optional = true)
//    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
//    private User user;
}