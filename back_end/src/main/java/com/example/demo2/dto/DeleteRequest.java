package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DeleteRequest {
    private Long uid; // user ID
    private Long oid; // object ID
}
