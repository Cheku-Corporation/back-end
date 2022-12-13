package com.cheku.cheku.model;

import javax.persistence.*;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "trip")
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "distance", nullable = false)
    private Double traveledDistance;
 
    @Column(name = "start_date", nullable = true)
    private Date startTime;

    @Column(name = "end_date", nullable = true)
    private Date endTime;

    @ManyToOne(optional = true)
    @JoinColumn(name = "car", nullable = true)
    private Car car;
}
