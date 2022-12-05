package com.cheku.cheku.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.io.Serializable;


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
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(optional = true)
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;
}
