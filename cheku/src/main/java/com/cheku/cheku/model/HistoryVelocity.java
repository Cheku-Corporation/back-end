package com.cheku.cheku.model;

import javax.persistence.*;
import java.util.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "historyVelocity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryVelocity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "velocity", nullable = false)
    private Double velocity;

    //-1 seria REVERSE
    //0 seria NONE
    @Column(name = "gear")
    private int gear;

    @Column(name = "date", nullable = false)
    private Date date;

    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Car car;
}
