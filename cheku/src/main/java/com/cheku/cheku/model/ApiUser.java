package com.cheku.cheku.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * Entity class for storing user data in the database.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiUser {

    /**
     * The unique identifier for this user in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user's email address.
     */
    @Column(name = "email", nullable = false, unique = true)
    @NotNull(message = "Email is required")
    private String email;

    /**
     * The user's password (encrypted).
     */
    @Column(name = "password", nullable = false)
    @NotNull(message = "Password is required")
    private String password;

    /**
     * The user's name.
     */
    @Column(name = "name", nullable = false)
    @NotNull(message = "Name is required")
    private String name;

    /**
     * The user's role (e.g. "admin", "moderator", "user").
     */
    @Column(name = "role", nullable = false)
    @NotNull(message = "Role is required")
    private String role;

    /**
     * The group that this user belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
