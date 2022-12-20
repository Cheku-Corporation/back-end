package com.cheku.cheku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * A group of users and cars.
 */
@Entity
@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    /**
     * The unique identifier for the group.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the group.
     */
    @Column(name = "groupName", nullable = false)
    private String groupName;

    /**
     * The list of cars in the group.
     */
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "cars", referencedColumnName = "id", nullable = true)
    private List<Car> carList = new ArrayList<>();

    /**
     * The list of users in the group.
     */
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "users", referencedColumnName = "id", nullable = true)
    private List<ApiUser> userList = new ArrayList<>();

    /**
     * The identifier of the user who is the admin of the group.
     */
    @Column(name = "admin", nullable = false)
    private long isAdmin;

    /**
     * Adds a user to the group.
     *
     * @param user the user to add
     * @throws RuntimeException if the user already exists in the group
     */
    public void addUser(@NonNull ApiUser user) {
        if (userList.contains(user)) {
            throw new RuntimeException("User already exists");
        }
        userList.add(user);
    }

    /**
     * Adds a car to the group.
     *
     * @param car the car to add
     * @throws RuntimeException if the car already exists in the group
     */
    public void addCar(@NonNull Car car) {
        if (carList.contains(car)) {
            throw new RuntimeException("Car already exists");
        }
        carList.add(car);
    }

    /**
     * Removes a user from the group.
     *
     * @param user the user to remove
     * @throws RuntimeException if the user does not exist in the group
     */
    public void removeUser(@NonNull ApiUser user) {
        if (!userList.contains(user)) {
            throw new RuntimeException("User does not exist");
        }
        userList.remove(user);
    }

    /**
     * Removes a car from the group.
     *
     * @param car the car to remove
     * @throws RuntimeException if the car does not exist in the group
     */
    public void removeCar(@NonNull Car car) {
        if (!carList.contains(car)) {
            throw new RuntimeException("Car does not exist");
        }
        carList.remove(car);
    }
}
