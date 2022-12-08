package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pneus_history")
public class PneusHistory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "date", nullable = false)
        private String date;

        @Column(name = "temperatura", nullable = false)
        private Double temperatura;

        @Column(name = "pressao", nullable = false)
        private Double pressao;

        @ManyToOne(optional = true)
        @JoinColumn(name = "car_id", nullable = true)
        private Car car;
}
