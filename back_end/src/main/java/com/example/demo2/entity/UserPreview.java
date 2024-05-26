package com.example.demo2.entity;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserPreview {
    private Long id = 0L;
    private String name = "";
    private String status = "";
    private String gender = ""; // M for male, F for female

    public UserPreview(){}
    public UserPreview(Long id, String name, String status, String gender){
        this.id = id;
        this.status = status;
        this.name = name;
        this.gender = gender;
    }
}
