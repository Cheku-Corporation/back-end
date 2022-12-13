package com.cheku.cheku.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tires_history")
public class PneusHistory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "date", nullable = false)
        private Date date;

        @Column(name = "temperature", nullable = false)
        private Double temperature;

        @Column(name = "pressure", nullable = false)
        private Double pressure;

        @ManyToOne(optional = true)
        @JoinColumn(name = "trip_id", nullable = true)
        private Trip trip;
}
