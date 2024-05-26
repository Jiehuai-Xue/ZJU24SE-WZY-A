package com.example.demo2.entity;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private Long id = 0L;
    private String name = "";
    private double credit = 0;
    private String time = "{\"1\":\"未设置\"}";
    private Long classroomId = 0L; 
    private String classroomName = "";// e.g. "曹西101" 
    private String campusName = ""; 
    private Long teacherId = 0L; 
    private String teacherName = "";// e.g. "李华"
    private Long capacity = 0L;
    private Long size = 0L; // 当前选课人数
    private boolean compulsory = false;
    private String AssessmentMethod = ""; // @todo 应该改为String[]

    public Course(){}

    public boolean ifCompulsory(){
        return this.compulsory;
    }
}
