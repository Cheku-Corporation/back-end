package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;
import com.cheku.cheku.model.enums.LightState;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "luzes")
public class LightsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state", nullable = false)
    private LightState state;

    @Column(name = "date", nullable = false)
    private Date date;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "trip_id", nullable = true)
    private Trip trip;
}
