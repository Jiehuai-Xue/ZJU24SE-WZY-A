package com.example.demo2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter@Setter
public class Student{
    private String cst = ""; // course selection tendency
    private String training_plan = "not set";
}
