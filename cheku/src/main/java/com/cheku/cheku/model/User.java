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
@Table(name = "users")
public class User {

    //dados estaticos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany()
    @JoinColumn(name = "cars", referencedColumnName = "id")
    private List<Car> carList;

    @OneToMany()
    @JoinColumn(name = "groups", referencedColumnName = "id")
    private List<Group> groupList;

}
