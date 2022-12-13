package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Brand is required")
    @Column(name = "brand", nullable = false)
    private String brand;

    @NotNull(message = "Model is required")
    @Column(name = "model", nullable = false)
    private String model;

}
