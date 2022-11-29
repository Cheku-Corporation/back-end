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
public class HistoryVelocity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "velocity", nullable = false)
    private Double velocity;

    public HistoryVelocity(Double velocity) {
        this.velocity = velocity;
    }

    //    //-1 seria REVERSE
//    //0 seria NONE
//    @Column(name = "gear")
//    private int gear;
//
//    @Column(name = "date"
//    private Date date;
//
//    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = true)
//    @ManyToOne(optional = true)
//    private Car car;

}
