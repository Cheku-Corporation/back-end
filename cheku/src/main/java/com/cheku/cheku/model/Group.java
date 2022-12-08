package com.cheku.cheku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;

@Entity
@Data
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
    @OneToMany
    @JoinColumn(name = "users", referencedColumnName = "id", nullable = true)
    private List<User> userList = new ArrayList<>();

    @Column(name = "admin", nullable = false)
    private long admin;


    public void addUser(User user) {
        //verificar se o user ja existe
        if (userList.contains(user)) {
            return;
        }
        userList.add(user);
    }

    public void addCar(Car car) {
        //verificar se o car ja existe
        if (carList.contains(car)) {
            return;
        }
        carList.add(car);
    }

    public void removeUser(User user) {
        //verificar se o user ja existe
        if (!userList.contains(user)) {
            return;
        }
        userList.remove(user);
    }

    public void removeCar(Car car) {
        //verificar se o car ja existe
        if (!carList.contains(car)) {
            return;
        }
        carList.remove(car);
    }

}
