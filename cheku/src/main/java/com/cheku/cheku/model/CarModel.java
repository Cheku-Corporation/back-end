package com.cheku.cheku.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cheku.cheku.model.enums.TypeCar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entity class representing a model of car.
 */
@Entity
@Table(name = "model_car")
@Data // generates getters, setters, toString, equals and hashCode methods
@NoArgsConstructor // generates a constructor with no arguments
@AllArgsConstructor // generates a constructor with all fields as arguments
public class CarModel {

    /**
     * The unique ID of the model of car.
     */
    @Id
    @NotNull(message = "Model is required")
    @Column(name = "model", nullable = false)
    private String model;

    /**
     * The brand of the car.
     */
    @NotNull(message = "Brand is required")
    @Column(name = "brand", nullable = false)
    private String brand;

    /**
     * The year the car was manufactured.
     */
    @NotNull(message = "Year is required")
    @Min(1900)
    @Column(name = "year", nullable = false)
    private int year;

    /**
     * The capacity of the fuel tank in liters.
     */
    @NotNull(message = "Fuel tank capacity is required")
    @Column(name = "TankCapacity", nullable = false)
    private Double tankCapacity;

    /**
     * The typeCombustible of the car.(gasolina, diesel, hibrido, electrico)
     */
    @NotNull(message = "TypeCombustible is required")
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCar type;

    /**
     * The Engine of the car.
     */
    @NotNull(message = "Engine is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "Engine", nullable = false)
    private Motor motor;

    /**
     * The tires of the car.
     */
    @NotNull(message = "Tires is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "Tires_id", nullable = false)
    private Pneus pneus;

}
