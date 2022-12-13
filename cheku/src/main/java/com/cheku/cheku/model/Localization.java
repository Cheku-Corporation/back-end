package com.cheku.cheku.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "localization")
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(optional = true)
    @JoinColumn(name = "trip_id", nullable = true)
    private Trip trip;
}
