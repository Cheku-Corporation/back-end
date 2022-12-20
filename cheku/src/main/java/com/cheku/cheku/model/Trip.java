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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a trip.
 */
@Entity
@Table(name = "trip")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    /** The unique ID of the trip. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The number of the trip. */
    @Column(name = "number", nullable = false)
    private Long number;

    /** The distance traveled during the trip. */
    @Column(name = "distance", nullable = false)
    private Double traveledDistance;

    /** The start time of the trip. */
    @Column(name = "start_date", nullable = true)
    private Date startTime;

    /** The end time of the trip. */
    @Column(name = "end_date", nullable = true)
    private Date endTime;

    /** The car that the trip is related to. */
    @ManyToOne(optional = true)
    @JoinColumn(name = "car", nullable = true)
    private Car car;
}
