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
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for storing fluid data.
 */
@Entity
@Table(name = "fluid")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fluid {

    /** The unique ID of the fluid.*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    /** The fuel level of the fluid. */
    @Column(name = "fuel", nullable = true)
    private Double fuel;

    /** The oil level of the fluid. */
    @NotNull(message = "Oil is required")
    @Column(name = "oil", nullable = false)
    private Double oil;

    /** The water level of the fluid.*/
    @NotNull(message = "Water is required")
    @Column(name = "water", nullable = false)
    private Double water;

    /** The date when the fluid data was recorded. */
    @NotNull(message = "Date is required")
    @Column(name = "date", nullable = false)
    private Date date;

    /** The trip that the fluid data is related to.*/
    @ManyToOne(optional = true)
    @JoinColumn(name = "trip_id", nullable = true)
    private Trip trip;
}
