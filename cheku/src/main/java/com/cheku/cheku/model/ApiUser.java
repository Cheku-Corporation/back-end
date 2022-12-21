package com.cheku.cheku.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a user.
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiUser {

    /** The unique identifier for the user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The user's email address. */
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    private String email;

    /** The user's password (encrypted). */
    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    /** The user's name. */
    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    /** The user's role (e.g. "admin", "moderator", "user"). */
    @Column(name = "role", nullable = false)
    @NotEmpty
    private String role;

    /** The group that this user belongs to. */
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true, referencedColumnName = "id")
    private Group group;
}
