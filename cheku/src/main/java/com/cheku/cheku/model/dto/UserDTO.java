package com.cheku.cheku.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * This class represents a data transfer object (DTO) for a user.
 */
@Data
@Builder
public final class UserDTO {

    /** The ID of the user. */
    private final Long userId;

    /** The name of the user. */
    private final String name;

    /** The email address of the user. */
    private final String email;

    /** The ID of the group the user belongs to. */
    private final Long groupId;

    /** The name of the group the user belongs to. */
    private final String groupName;
}

