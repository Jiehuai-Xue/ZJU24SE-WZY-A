package com.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.*;

import com.example.demo2.dto.GetByIdRequest;
import com.example.demo2.dto.GetCourseResponse;
import com.example.demo2.dto.GetCoursesResponse;
import com.example.demo2.dto.GetRequest;
import com.example.demo2.service.StudentService;
import com.example.demo2.service.CourseService;
import com.example.demo2.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "http://127.0.0.1/:5173")
@RestController
@RequestMapping("/student")  
public class StudentController {

    private StudentService studentService;
    private CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {  
        this.studentService = studentService;  
        this.courseService = courseService;
    }

    @PostMapping("/show_courses")
    public GetCoursesResponse getCourses(@RequestBody GetByIdRequest request) {
        System.out.println("get a /student/show_courses request");
        GetCoursesResponse response = studentService.showCourses(request.getOid());
        return response;
    }
    
    @PostMapping("/show_course")
    public GetCourseResponse getCourse(@RequestBody GetByIdRequest getByIdRequest) {
        System.out.println("get a /student/show_course request");
        GetCourseResponse response = courseService.getCourse(getByIdRequest.getOid());
        return response;
    }
    
}
