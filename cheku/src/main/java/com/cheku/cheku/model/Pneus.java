package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "pneus")
@AllArgsConstructor
@NoArgsConstructor
public class Pneus {

    //dados estaticos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

}
