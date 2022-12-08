package com.cheku.cheku.model;

import com.cheku.cheku.model.enums.StateMotor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "motor_history")
public class MotorHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "temperatura", nullable = false)
    private Double temperatura;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private StateMotor state;

    @ManyToOne(optional = true)
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;

}
