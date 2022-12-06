package com.cheku.cheku.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

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


    //lista de cars
    @OneToMany
    @JoinColumn(name = "cars", referencedColumnName = "id", nullable = true)
    private List<Car> carList;


    //lista de users
    @OneToMany()
    @JoinColumn(name = "users", referencedColumnName = "id", nullable = false)
    private List<User> users;

    //admin
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "admin", referencedColumnName = "id", nullable = false)
//    private User admin;


}
