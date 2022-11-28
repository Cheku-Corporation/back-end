package com.cheku.cheku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;


@Entity
@Data
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    //lista de users
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "users", referencedColumnName = "id", nullable = false)
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cars", referencedColumnName = "id", nullable = false)
    private List<Car> carList;

}
