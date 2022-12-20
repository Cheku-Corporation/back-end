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

    @Column(name = "inspectionDate", nullable = false)
    private Date inspectionDate;

    @Column(name = "insuranceDate", nullable = false)
    private Date insuranceDate;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group ;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false, referencedColumnName = "model")
    private CarModel carModel;

    public void setCar(Car car) {
        this.matricula = car.getMatricula();
        this.inspectionDate = car.getInspectionDate();
        this.insuranceDate = car.getInsuranceDate();
        this.group = car.getGroup();
        this.carModel = car.getCarModel();
    }
}