package com.example.demo2.entity;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CoursePreview {
    private Long id = 0L;
    private String name = "";
    private double credit = 0;
    private String time = "";
    private String classroom = ""; // e.g. "曹西101" 
    private String teacher = ""; // e.g. "李华"
}
