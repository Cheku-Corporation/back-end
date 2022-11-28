package com.cheku.cheku.model;

import lombok.Data;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;


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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    @JoinColumn(name = "users", referencedColumnName = "id", nullable = false)
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    @JoinColumn(name = "cars", referencedColumnName = "id", nullable = false)
    private List<Car> carList;

}
