package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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

    @Column(name = "percentagem", nullable = false)
    private Double percentagem;

    @NotNull(message = "Date is required")
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(optional = true)
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;

}
