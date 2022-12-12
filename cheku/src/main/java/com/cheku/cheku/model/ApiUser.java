package com.cheku.cheku.model;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class ApiUser {

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

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToMany()
    @JoinTable(name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groupList = new ArrayList<>();

    public void addGroup(Group groupToCreate) {
        //verificar se o grupo ja existe
        if (groupList.contains(groupToCreate)) {
            throw new RuntimeException("Grupo já existe");
        }
        groupList.add(groupToCreate);
    }
}
