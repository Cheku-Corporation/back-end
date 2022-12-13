package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fluid")
public class Fluid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fuel", nullable = true)
    private Double fuel;

    @NotNull(message = "Oil is required")
    @Column(name = "oil", nullable = false)
    private Double oil;

    @NotNull(message = "Water is required")
    @Column(name = "water", nullable = false)
    private Double water;

    @NotNull(message = "Date is required")
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(optional = true)
    @JoinColumn(name = "trip_id", nullable = true)
    private Trip trip;

}
