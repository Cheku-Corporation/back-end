package com.cheku.cheku.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable{

    @Id
    @Column(name = "nif", nullable = false)
    private long nif;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "cars")
    @OneToMany
    private List<Car> cars;

    //Constructors
    public User() {
    }

    public User(long nif, String name, String email, String password) {
        this.nif = nif;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
