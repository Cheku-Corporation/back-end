package com.cheku.cheku.model;

import com.cheku.cheku.model.enums.TypeCar;
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
@Table(name = "model_car")
public class CarModel {

    //Atributos estaticos

    @Column(name = "brand", nullable = false)
    private String brand;

    //Primary Key é o model
    @Id
    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "TankCapacity", nullable = false)
    private Double tankCapacity;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCar type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "motor", nullable = false)
    private Motor motor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pneus", nullable = false)
    private Pneus pneus;

}