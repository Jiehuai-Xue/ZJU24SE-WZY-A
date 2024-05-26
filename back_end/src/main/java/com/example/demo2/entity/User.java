package com.example.demo2.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter
@Entity
public class User {
    @Id
    private Long id = 0L;
    private String name = "";
    private String status = "";
    private String password = "";
    private String gender = "";
    private int age = 0; // M for male, F for female
    private String phone = "";
    private String email = "";
    private String address = "";


    public User(){}
}
