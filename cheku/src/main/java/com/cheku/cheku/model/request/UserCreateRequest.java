package com.cheku.cheku.model.request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String Email;
    private String password;
    private String role;
    private String name;

}