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
 * Entity class for storing localization data.
 */
@Entity
@Table(name = "localization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    /** The unique ID of the localization record. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The latitude of the location. */
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    /** The longitude of the location. */
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    /** The date when the localization data was recorded. */
    @Column(name = "date", nullable = false)
    private Date date;

    /** The trip that the localization data is related to. */
    @ManyToOne(optional = true)
    @JoinColumn(name = "trip_id", nullable = true)
    private Trip trip;
}
