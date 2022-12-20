package com.cheku.cheku.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    /**
     * The unique ID of the car.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The license plate number of the car.
     */
    @Size(min = 8, max = 8)
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    /**
     * The date of the last inspection for the car.
     */
    @Column(name = "inspectionDate", nullable = false)
    private Date inspectionDate;

    /**
     * The date of the last insurance for the car.
     */
    @Column(name = "insuranceDate", nullable = false)
    private Date insuranceDate;

    /**
     * The group that the car belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    /**
     * The model of the car.
     */
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false, referencedColumnName = "model")
    private CarModel carModel;
}
