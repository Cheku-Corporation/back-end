package com.cheku.cheku.model;

import com.cheku.cheku.model.enums.TypeCar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

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

    @Size(min = 8, max = 8)
    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column(name = "TankCapacity", nullable = false)
    private Double tankCapacity;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCar type;

    @Column(name = "dateofinspection", nullable = false)
    private Date dateofinspection;

    @Column(name = "dateofinsurance", nullable = false)
    private Date dateofinsurance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "motor", nullable = false)
    private Motor motor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pneus", nullable = false)
    private Pneus pneus;

    @ManyToMany
    @JoinTable(name = "car_user",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groupList = new ArrayList<>();


    public void addGroup(Group group) {
        groupList.add(group);
    }


}