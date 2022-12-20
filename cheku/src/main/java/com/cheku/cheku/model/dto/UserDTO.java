package com.cheku.cheku.model.dto;

import lombok.Data;

/**
 * This class represents a data transfer object (DTO) for a user.
 */
@Data
public final class UserDTO {

    /** The ID of the user. */
    private  Long userId;

    /** The name of the user. */
    private  String name;

    /** The email address of the user. */
    private  String email;

    /** The ID of the group the user belongs to. */
    private  Long groupId;

    /** The name of the group the user belongs to. */
    private  String groupName;
}

