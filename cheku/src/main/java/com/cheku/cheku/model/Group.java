package com.cheku.cheku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;


    @JsonIgnore
    //lista de cars
    @OneToMany
    @JoinColumn(name = "cars", referencedColumnName = "id", nullable = true)
    private List<Car> carList = new ArrayList<>();

    //lista de users
    @JsonIgnore
    @ManyToMany(mappedBy = "groupList")
    private List<ApiUser> userList = new ArrayList<>();

    @Column(name = "admin", nullable = false)
    private long admin;

    public void addUser(ApiUser user) {
        //verificar se o user ja existe
        if (userList.contains(user)) {
            throw new RuntimeException("User already exists");
        }
        userList.add(user);
    }

    public void addCar(Car car) {
        //verificar se o car ja existe
        if (carList.contains(car)) {
            throw new RuntimeException("Carro já existe");
        }
        carList.add(car);
    }

    public void removeUser(ApiUser user) {
        //verificar se o user ja existe
        if (!userList.contains(user)) {
            throw new RuntimeException("User não existe");
        }
        userList.remove(user);
    }

    public void removeCar(Car car) {
        //verificar se o car ja existe
        if (!carList.contains(car)) {
            throw new RuntimeException("Carro não existe");
        }
        carList.remove(car);
    }

}