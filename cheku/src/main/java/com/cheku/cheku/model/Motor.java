package com.cheku.cheku.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

/**
 * Entity class representing a motor.
 */
@Entity
@Table(name = "motors")
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Motor {

    /** The unique identifier for the motor. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** The power of the motor, typically measured in horsepower. */
    @Column(name = "power", nullable = false)
    @NotNull(message = "Power is required")
    private int power;

    /** The displacement of the motor, typically measured in cubic centimeters (cc). */
    @Column(name = "displacement", nullable = false)
    @NotNull(message = "Displacement is required")
    private int displacement;

    /** The model of the motor. */
    @Column(name = "model", nullable = false)
    @NotNull(message = "Model is required")
    private String model;
}
