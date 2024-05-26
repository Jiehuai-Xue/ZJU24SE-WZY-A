package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;

import com.example.demo2.entity.Course;

@Getter@Setter
public class AddCourseRequest {
    private Long id = 0L;
    private Course course;
}
