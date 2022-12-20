package com.cheku.cheku.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

/**
 * Entity class representing a tire.
 */
@Entity
@Table(name = "tires")
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tires {

    /**
     * The unique identifier for the tire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The brand of the tire.
     */
    @NotNull(message = "Brand is required")
    @Column(name = "brand", nullable = false)
    private String brand;

    /**
     * The model of the tire.
     */
    @NotNull(message = "Model is required")
    @Column(name = "model", nullable = false)
    private String model;
}
