package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;

import com.example.demo2.entity.User;

@Getter@Setter
public class AddUserRequest {
    private Long id = 0L;
    private User user;
}
