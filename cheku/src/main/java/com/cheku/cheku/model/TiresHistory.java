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
 * Entity class for storing tire history data.
 */
@Entity
@Table(name = "tires_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiresHistory {

        /** The unique ID of the tire history record. */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /** The date when the tire data was recorded. */
        @Column(name = "date", nullable = false)
        private Date date;

        /** The temperature of the tires. */
        @Column(name = "temperature", nullable = false)
        private Double temperature;

        /** The pressure of the tires. */
        @Column(name = "pressure", nullable = false)
        private Double pressure;

        /** The trip that the tire history is related to. */
        @ManyToOne(optional = true)
        @JoinColumn(name = "trip_id", nullable = true)
        private Trip trip;
}
