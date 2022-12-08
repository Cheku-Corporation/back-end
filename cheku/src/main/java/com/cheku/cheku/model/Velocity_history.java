package com.cheku.cheku.model;

import javax.persistence.*;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "historyVelocity")
public class Velocity_history {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "velocity", nullable = false)
    private Double velocity;

    //-1 seria REVERSE
    //0 seria NONE
    @Column(name = "gear", nullable = false)
    private int gear;

    @Column(name = "date", nullable = false)
    private Date date;

//    @Column(name = "RPM", nullable = false)
//    private Double RPM;

    @ManyToOne(optional = true)
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;
}
