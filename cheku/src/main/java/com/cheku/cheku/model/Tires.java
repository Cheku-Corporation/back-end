package com.cheku.cheku.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entity class representing a tire.
 */
@Entity
@Table(name = "tires")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
