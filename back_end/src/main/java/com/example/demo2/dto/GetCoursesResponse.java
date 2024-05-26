package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

import com.example.demo2.entity.CoursePreview;

@Getter
@Setter
public class GetCoursesResponse {
    private String code = "";
    private String message = "";
    private ArrayList<CoursePreview> courses;

    public GetCoursesResponse(){}

    public void appendMessage(String message){
        this.message += ("\n" + message);
    }

    public void setCourses(ArrayList<CoursePreview> courses){
        this.courses = courses;
    }
}
