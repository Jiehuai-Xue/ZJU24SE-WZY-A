package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

import com.example.demo2.entity.UserPreview;

@Getter
@Setter
public class GetUsersResponse {
    private String code = "";
    private String message = "";
    private ArrayList<UserPreview> data;
}
