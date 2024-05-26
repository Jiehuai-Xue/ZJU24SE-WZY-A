package com.example.demo2.controller;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;  
import org.springframework.web.bind.annotation.*;  

import com.example.demo2.service.AdminService;
import com.example.demo2.dto.GetRequest;
import com.example.demo2.dto.GetUsersResponse;
import com.example.demo2.dto.AddClassroomRequest;
import com.example.demo2.dto.AddCourseRequest;
import com.example.demo2.dto.AddUserRequest;
import com.example.demo2.dto.BasicResponse;
import com.example.demo2.dto.DeleteRequest;
import com.example.demo2.dto.GetCoursesResponse;
import com.example.demo2.dto.GetClassroomsResponse;


@CrossOrigin(origins = "http://http://127.0.0.1/:5173")
@RestController  
@RequestMapping("/admin")  
public class AdminController {
    private final AdminService adminService;  
  
    @Autowired   // 尝试自动装配该bean的依赖项
    public AdminController(AdminService adminService) {  
        this.adminService = adminService;  
    } 

    /* 添加用户 */
    @PostMapping("/user_management/add")
    public BasicResponse addUser(@RequestBody AddUserRequest request) {
        System.out.println("\nget a admin/user_management/add request...");
        BasicResponse response = adminService.addUser(request.getId(), request.getUser());
        return response;
    }

    /* 添加课程 */
    @PostMapping("/course_management/add")
    public BasicResponse addCourse(@RequestBody AddCourseRequest request) {
        System.out.println("\nget a admin/course_management/add request...");
        BasicResponse response = adminService.addCourse(request.getId(), request.getCourse());
        return response;
    }

    /* 添加教室 */
    @PostMapping("/classroom_management/add")
    public BasicResponse addClassroom(@RequestBody AddClassroomRequest request) {
        System.out.println("\nget a admin/classroom_management/add request...");
        BasicResponse response = adminService.addClassroom(request.getId(), request.getClassroom());
        return response;
    }
    
    /* 删除用户 */
    @PostMapping("/user_management/delete")
    public BasicResponse deleteUser(@RequestBody DeleteRequest request) {
        System.out.println("\nget a admin/user_management/delete request...");
        BasicResponse response = adminService.deleteUser(request.getUid(), request.getOid());
        return response;
    }
    
    /* 删除课程 */
    @PostMapping("/course_management/delete")
    public BasicResponse deleteCourse(@RequestBody DeleteRequest request) {
        System.out.println("\nget a admin/course_management/delete request...");
        BasicResponse response = adminService.deleteCourse(request.getUid(), request.getOid());
        return response;
    }
    
    /* 删除教室 */
    @PostMapping("/classroom_management/delete")
    public BasicResponse deleteClassroom(@RequestBody DeleteRequest request) {
        System.out.println("\nget a admin/classroom_management/delete request...");
        BasicResponse response = adminService.deleteClassroom(request.getUid(), request.getOid());
        return response;
    }

    /* 默认查询前20个用户 */
    @PostMapping("/user_management/")  
    public ResponseEntity<?> getUsersByDefault(@RequestBody GetRequest request) {   // ?是通配符，表示未知类型
        System.out.println("\nget a admin/user_management/ request...");
        GetUsersResponse response = adminService.getUsersByDefault();
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }
    
    /* 根据姓名来查询用户 */
    @PostMapping("/user_management/get_by_name")  
    public ResponseEntity<?> getUsersByName(@RequestBody GetRequest request) {   // ?是通配符，表示未知类型
        System.out.println("\nget a admin/user_management/get_by_name request...");
        GetUsersResponse response = adminService.getUsersByDefault();
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }

    /* 根据性别来筛选用户 */
    @PostMapping("/user_management/get_by_gender")  
    public ResponseEntity<?> getUsersByGender(@RequestBody GetRequest request) {   // ?是通配符，表示未知类型
        System.out.println("\nget a admin/user_management/get_by_gender request...");
        GetUsersResponse response = adminService.getUsersByGender(request.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* 返回前20个课程 */
    @PostMapping("/course_management/")  
    public ResponseEntity<?> getCoursesByDefault(@RequestBody GetRequest request) {   // ?是通配符，表示未知类型
        System.out.println("\nget a admin/course_management/ request...");
        GetCoursesResponse response = adminService.getCoursesByDefault();
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }

    /* 根据名字查询课程 */
    @PostMapping("/course_management/get_by_name")  
    public ResponseEntity<?> getCoursesByName(@RequestBody GetRequest request) {   // ?是通配符，表示未知类型
        System.out.println("\nget a admin/course_management/get_by_name request...");
        GetCoursesResponse response = adminService.getCoursesByName(request.getData());
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }

    /* 默认查询前20个教室 */
    @PostMapping("/classroom_management/")  
    public ResponseEntity<?> getClassroomByDefault(@RequestBody GetRequest request) {   // ?是通配符，表示未知类型
        System.out.println("\nget a admin/classroom_management/ request...");
        GetClassroomsResponse response = adminService.getClassroomsByDefault();
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }
}
