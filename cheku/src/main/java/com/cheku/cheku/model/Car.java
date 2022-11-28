package com.cheku.cheku.model;

import com.cheku.cheku.model.enums.TypeCar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car  {

    //Atributos estaticos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;

    //Relacionamentos (verificar)
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TypeCar type;

    @OneToOne(optional = false)
    @JoinColumn(name = "motor_id", referencedColumnName = "id", nullable = false)
    private Motor motor;

    @OneToOne(optional = false)
    @JoinColumn(name = "pneu_id", referencedColumnName = "id", nullable = false)
    private Pneus pneus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private User user;

}


