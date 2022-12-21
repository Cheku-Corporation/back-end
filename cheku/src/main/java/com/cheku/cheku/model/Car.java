package com.cheku.cheku.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a car.
 */
@Entity
@Table(name = "cars")
@Data // generates getters, setters, toString, equals and hashCode methods
@NoArgsConstructor // generates a constructor with no arguments
@AllArgsConstructor // generates a constructor with all fields as arguments
public class Car {

    /** The unique ID of the car.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The license plate number of the car. */
    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "matricula", unique = true)
    private String matricula;

    /** The date of the last inspection for the car.*/
    @NotNull
    @Column(name = "inspection_date")
    @Temporal(TemporalType.DATE)
    private Date inspectionDate;

    /** The date of the last insurance for the car.*/
    @NotNull
    @Column(name = "insurance_date")
    @Temporal(TemporalType.DATE)
    private Date insuranceDate;


     /** The group that the car belongs to.*/
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false, referencedColumnName = "id")
    private Group group;

    /** The model of the car.*/
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false, referencedColumnName = "model")
    private CarModel carModel;
}
