package com.cheku.cheku.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long idUser;
    private String name;
    private String email;
    private Long groupId;
    private String groupName;

}
