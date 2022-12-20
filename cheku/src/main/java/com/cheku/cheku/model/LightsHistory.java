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

import com.cheku.cheku.model.enums.LightState;

/**
 * Entity class for storing lights history data.
 */
@Entity
@Table(name = "lights_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LightsHistory {

    /** The unique ID of the lights history record. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The state of the lights. */
    @Column(name = "state", nullable = false)
    private LightState state;

    /** The date when the lights state was recorded. */
    @Column(name = "date", nullable = false)
    private Date date;

    /** The trip that the lights history is related to. */
    @ManyToOne(optional = true)
    @JoinColumn(name = "trip_id", nullable = true)
    private Trip trip;
}
