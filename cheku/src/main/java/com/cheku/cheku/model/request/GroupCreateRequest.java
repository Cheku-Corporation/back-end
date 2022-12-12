package com.cheku.cheku.model.request;

import lombok.Data;

@Data
public class GroupCreateRequest {
    private String name;
    private long admin;

}
