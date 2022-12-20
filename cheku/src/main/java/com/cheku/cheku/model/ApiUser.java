package com.cheku.cheku.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Entity class for storing user data in the database.
 */
@Entity
@Table(name = "users")
@Data
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
    @NotNull(message = "User type is required")
    private String role;

    /**
     * The group that this user belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /**
     * Validates the email field to ensure that it is in the correct format.
     * @return true if the email is in the correct format, false otherwise
     */
    public boolean isEmailValid() {
        // Use a regex pattern to check the email format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Encrypts the password using the BCrypt algorithm.
     */
    public void encryptPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }
}
