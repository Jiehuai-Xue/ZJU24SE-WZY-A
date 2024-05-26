package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;

import com.example.demo2.entity.Classroom;;

@Getter@Setter
public class AddClassroomRequest {
    private Long id = 0L;
    private Classroom classroom;
}
