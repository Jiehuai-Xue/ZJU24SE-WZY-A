package com.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.dto.GetByIdRequest;
import com.example.demo2.dto.GetCourseResponse;
import com.example.demo2.dto.GetCoursesResponse;
import com.example.demo2.dto.GetRequest;
import com.example.demo2.service.CourseService;
import com.example.demo2.service.StudentService;

import com.example.demo2.service.TeacherService;

@CrossOrigin(origins = "http://http://127.0.0.1/:5173")
@RestController
@RequestMapping("/teacher")  
public class TeacherController {

    private TeacherService teacherService;
    private CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {  
        this.teacherService = teacherService;  
        this.courseService = courseService;
    }

    @PostMapping("/show_courses")
    public GetCoursesResponse postMethodName(@RequestBody GetByIdRequest request) {
        System.out.println("get a /teacher/show_courses request");
        GetCoursesResponse response = teacherService.showCourses(request.getOid());
        return response;
    }

    @PostMapping("/show_course")
    public GetCourseResponse getCourse(@RequestBody GetByIdRequest getByIdRequest) {
        System.out.println("get a /teacher/show_course request");
        GetCourseResponse response = courseService.getCourse(getByIdRequest.getOid());
        return response;
    }
    
}
