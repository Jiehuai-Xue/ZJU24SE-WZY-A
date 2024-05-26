package com.example.demo2.entity;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Classroom {
    private Long id = 0L;
    private String name = "";
    private String campus = "";
    private Long capacity = 0L;
    private String condition = "无设备";
}
