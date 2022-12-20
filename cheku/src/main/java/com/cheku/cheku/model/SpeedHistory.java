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
 * Entity class for storing speed history data.
 */
@Entity
@Table(name = "speed_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeedHistory {

    /** The unique ID of the speed history record. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The velocity of the vehicle. */
    @Column(name = "velocity", nullable = false)
    private Double velocity;

    /** The gear of the vehicle. A value of -1 represents reverse gear, and a value of 0 represents no gear. */
    @Column(name = "gear", nullable = false)
    private int gear;

    /** The date when the speed data was recorded. */
    @Column(name = "date", nullable = false)
    private Date date;

    /** The RPM of the engine. */
    @Column(name = "RPM", nullable = false)
    private Double RPM;

    /** The trip that the speed history is related to. */
    @ManyToOne(optional = true)
    @JoinColumn(name = "trip_id", nullable = true)
    private Trip trip;
}
