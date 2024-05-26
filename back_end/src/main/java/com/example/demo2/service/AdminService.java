package com.example.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo2.dto.BasicResponse;
import com.example.demo2.dto.GetClassroomsResponse;
import com.example.demo2.dto.GetCoursesResponse;
import com.example.demo2.dto.GetUsersResponse;
import com.example.demo2.source.UserRepository;
import com.example.demo2.entity.CoursePreview;
import com.example.demo2.entity.Classroom;
import com.example.demo2.entity.User;
import com.example.demo2.entity.Log;
import com.example.demo2.entity.UserPreview;
import com.example.demo2.source.CourseRepository;
import com.example.demo2.source.LogRepository;
import com.example.demo2.entity.Course;
import com.example.demo2.source.ClassroomRepository;
import com.example.demo2.source.TeacherRepository;
import com.example.demo2.source.StudentRepository;

@Service
public class AdminService {

    @Autowired private UserRepository userRepository; 
    @Autowired private CourseRepository courseRepository; 
    @Autowired private ClassroomRepository classroomRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private LogRepository logRepository;

    /* 添加用户 */
    public BasicResponse addUser(Long uid, User user){
        BasicResponse response = new BasicResponse();
        if(! user.getPassword().equals("")){
            System.out.println("[WARNING] password != empty");
        }
        if(!((user.getStatus().equals("T") || user.getStatus().equals("S")))){
            response.setCode("-1");
            response.setMessage("invalid user identity");
            return response;            
        }
        user.setPassword("12345678"); // 初始密码
        Long id = userRepository.add(user);
        if(user.getStatus().equals("T")){
            teacherRepository.add(id);
        }else if(user.getStatus().equals("S")){
            studentRepository.add(id);
        }
        logRepository.add(new Log(uid % 100000000L, new Timestamp(System.currentTimeMillis()), "add user " + id));
        response.setCode("1");
        response.setMessage("successfully add");
        return response;
    }

    /* 添加课程 */
    public BasicResponse addCourse(Long id, Course course){
        BasicResponse response = new BasicResponse();
        Long course_id = courseRepository.add(course);
        logRepository.add(new Log(id % 100000000L, new Timestamp(System.currentTimeMillis()), "add course " + course_id));
        response.setCode("1");
        response.setMessage("successfully add");
        return response;
    }

    /* 添加教室 */
    public BasicResponse addClassroom(Long id, Classroom classroom){
        BasicResponse response = new BasicResponse();
        Long classroom_id = classroomRepository.add(classroom);
        logRepository.add(new Log(id % 100000000L, new Timestamp(System.currentTimeMillis()), "add classroom " + classroom_id));
        response.setCode("1");
        response.setMessage("successfully add");
        return response;
    }

    /* 删除用户 */
    public BasicResponse deleteUser(Long uid, Long oid){
        BasicResponse response = new BasicResponse();
        Long first_digit_uid = uid / 100000000L;
        Long first_digit_oid = oid / 100000000L;
        if(first_digit_uid != 2){
            response.setCode("-1");
            response.setMessage("permisiion denied");
        }else if(first_digit_oid == 2 || first_digit_oid == 1){
            response.setCode("-1");
            response.setMessage("permisiion denied");
        }else{
            userRepository.deleteUser(oid);
            logRepository.add(new Log(uid % 100000000L, new Timestamp(System.currentTimeMillis()), "delete user " + oid));
            response.setCode("1");
            response.setMessage("successfully delete");            
        }
        return response;
    }

    /* 删除课程 */
    public BasicResponse deleteCourse(Long uid, Long oid){
        BasicResponse response = new BasicResponse();
        courseRepository.delete(oid);
        logRepository.add(new Log(uid % 100000000L, new Timestamp(System.currentTimeMillis()), "delete course " + oid));
        response.setCode("1");
        response.setMessage("successfully delete");
        return response;
    }

    /* 删除教室 */
    public BasicResponse deleteClassroom(Long uid, Long oid){
        BasicResponse response = new BasicResponse();
        classroomRepository.delete(oid);
        logRepository.add(new Log(uid % 100000000L, new Timestamp(System.currentTimeMillis()), "delete classroom " + oid));
        response.setCode("1");
        response.setMessage("successfully delete");
        return response;
    }

    /* 默认查询前20个用户 */
    public GetUsersResponse getUsersByDefault(){
        GetUsersResponse response = new GetUsersResponse();
        List<Map<String, Object>> sql_result = userRepository.getUserPreviewsByDefault();
        response.setCode("1");
        response.setMessage("successfully query");
        ArrayList<UserPreview> list = new ArrayList<UserPreview>();
        for (Map<String, Object> line : sql_result){
            String status = line.get("status").toString();
            Long factor = 100000000L;
            if(status.equals("T")){
                factor = factor * 3;
            }else if(status.equals("S")){
                factor = factor * 4;
            }else if(status.equals("A")){
                factor = factor * 2;
            }else if(status.equals("SA")){
            }
            list.add(new UserPreview((Long)(line.get("UserId")) + factor, line.get("Name").toString(), line.get("status").toString(), line.get("Gender").toString()));
        }
        response.setData(list);
        return response;
    }

    /* 根据姓名来查询用户 */
    public GetUsersResponse getUsersByName(String name){
        GetUsersResponse response = new GetUsersResponse();
        List<Map<String, Object>> sql_result = userRepository.getUserPreviewsByName(name);
        response.setCode("1");
        response.setMessage("successfully query");
        ArrayList<UserPreview> list = new ArrayList<UserPreview>();
        for (Map<String, Object> line : sql_result){
            String status = line.get("status").toString();
            Long factor = 100000000L;
            if(status.equals("T")){
                factor = factor * 3;
            }else if(status.equals("S")){
                factor = factor * 4;
            }else if(status.equals("A")){
                factor = factor * 2;
            }else if(status.equals("SA")){
            }
            list.add(new UserPreview((Long)(line.get("UserId")) + factor, line.get("Name").toString(), line.get("status").toString(), line.get("Gender").toString()));
        }
        response.setData(list);
        return response;
    }

    /* 根据性别来筛选用户 */
    public GetUsersResponse getUsersByGender(String gender){
        GetUsersResponse response = new GetUsersResponse();
        List<Map<String, Object>> sql_result = userRepository.getUserPreviewsByGender(gender);
        response.setCode("1");
        response.setMessage("successfully query");
        ArrayList<UserPreview> list = new ArrayList<UserPreview>();
        for (Map<String, Object> line : sql_result){
            String status = line.get("status").toString();
            Long factor = 100000000L;
            if(status.equals("T")){
                factor = factor * 3;
            }else if(status.equals("S")){
                factor = factor * 4;
            }else if(status.equals("A")){
                factor = factor * 2;
            }else if(status.equals("SA")){
            }
            list.add(new UserPreview((Long)(line.get("UserId")) + factor, line.get("Name").toString(), line.get("status").toString(), line.get("Gender").toString()));
        }
        response.setData(list);
        return response;
    }

    /* 返回前20个课程 */
    public GetCoursesResponse getCoursesByDefault(){
        GetCoursesResponse response = new GetCoursesResponse();
        List<Map<String, Object>> sql_result = courseRepository.getCoursesByDefault();
        response.setCode("1");
        response.setMessage("successfully query");
        ArrayList<CoursePreview> list = new ArrayList<CoursePreview>();
        for (Map<String, Object> line : sql_result){
            CoursePreview coursePreview = new CoursePreview();
            coursePreview.setId((Long)(line.get("CourseId")));
            coursePreview.setName(line.get("CourseName").toString());
            coursePreview.setCredit((double)line.get("Credit"));
            coursePreview.setTime(line.get("CourseTime").toString());
            Long classroomId = (Long)(line.get("ClassroomID"));
            String classroomName = classroomRepository.getNameById(classroomId);
            coursePreview.setClassroom(classroomName);
            Long teacherId = (Long)(line.get("CourseTeacherID"));
            String teacherName = userRepository.getNameById(teacherId);
            coursePreview.setTeacher(teacherName);
            list.add(coursePreview);
        }
        response.setCourses(list);
        return response;
    }

    /* 根据课程名字来查找课程 */
    public GetCoursesResponse getCoursesByName(String name){
        GetCoursesResponse response = new GetCoursesResponse();
        List<Map<String, Object>> sql_result = courseRepository.getCoursesByName(name);
        response.setCode("1");
        response.setMessage("successfully query");
        ArrayList<CoursePreview> list = new ArrayList<CoursePreview>();
        for (Map<String, Object> line : sql_result){
            CoursePreview coursePreview = new CoursePreview();
            coursePreview.setId((Long)(line.get("CourseId")));
            coursePreview.setName(name);
            coursePreview.setCredit((double)line.get("Credit"));
            coursePreview.setTime(line.get("CourseTime").toString());
            Long classroomId = (Long)(line.get("ClassroomID"));
            String classroomName = classroomRepository.getNameById(classroomId);
            coursePreview.setClassroom(classroomName);
            Long teacherId = (Long)(line.get("CourseTeacherID"));
            String teacherName = userRepository.getNameById(teacherId);
            coursePreview.setTeacher(teacherName);
            list.add(coursePreview);
        }
        response.setCourses(list);
        return response;
    }

    /* 默认查询前20个教室 */
    public GetClassroomsResponse getClassroomsByDefault(){
        GetClassroomsResponse response = new GetClassroomsResponse();
        List<Map<String, Object>> sql_result = classroomRepository.getByDefault();
        response.setCode("1");
        response.setMessage("successfully query");
        ArrayList<Classroom> list = new ArrayList<Classroom>();
        for (Map<String, Object> line : sql_result){
            Classroom classroom = new Classroom();
            classroom.setId((Long)(line.get("ClassroomID")));
            classroom.setName(line.get("ClassroomName").toString());
            classroom.setCampus(line.get("CampusInformation").toString());
            classroom.setCapacity((Long)(line.get("ClassroomCapacity")));
            //classroom.setCondition(line.get(" Special_Conditions_of_Classrooms").toString());
            list.add(classroom);
        }
        response.setClassrooms(list);
        return response;
    }
}