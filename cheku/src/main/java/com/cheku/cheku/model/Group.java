package com.cheku.cheku.model;

import com.cheku.cheku.model.User;
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
    @OneToMany
    @JoinColumn(name = "users", referencedColumnName = "id", nullable = true)
    private List<User> userList;

    //colocar um user como admin
    @ManyToOne(optional = true)
    @JoinColumn(name = "admin", nullable = false)
    private User admin;


}
