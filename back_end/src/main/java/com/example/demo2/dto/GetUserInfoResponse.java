package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;

import com.example.demo2.entity.User;

@Getter@Setter
public class GetUserInfoResponse {
    private String code = "";
    private String message = "";
    private User data;
}
