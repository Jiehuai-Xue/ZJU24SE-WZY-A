package com.example.demo2.dto;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

import com.example.demo2.entity.Classroom;

@Getter@Setter
public class GetClassroomsResponse {
    private String code ="1";
    private String message = "";
    private ArrayList<Classroom> classrooms;

    public void setClassrooms(ArrayList<Classroom> classrooms){
        this.classrooms = classrooms;
    }
}
