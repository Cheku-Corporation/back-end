package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import com.cheku.cheku.model.enums.StateLuzes;

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
    private StateLuzes state;

    @ManyToOne(optional = true)
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;

}
