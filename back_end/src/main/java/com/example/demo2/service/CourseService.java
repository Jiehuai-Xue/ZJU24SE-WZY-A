package com.example.demo2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.dto.GetCourseResponse;
import com.example.demo2.dto.GetCoursesResponse;
import com.example.demo2.entity.Course;
import com.example.demo2.entity.CoursePreview;
import com.example.demo2.source.ClassroomRepository;
import com.example.demo2.source.CourseRepository;
import com.example.demo2.source.UserRepository;

@Service
public class CourseService {

    @Autowired private UserRepository userRepository;
    @Autowired CourseRepository courseRepo;
    @Autowired ClassroomRepository classroomRepo;

    /* 显示前20条课程信息列表 */
    public GetCoursesResponse showCourses(Long id){
        GetCoursesResponse response = new GetCoursesResponse();
        response.setCode("1");
        /* query to datebase */
        List<Map<String, Object>> sql_result = courseRepo.get20();
        ArrayList<CoursePreview> course_preview_list = new ArrayList<>();
        for(int i = 0; i < sql_result.size(); i ++){
            Long course_id = (Long)(sql_result.get(i).get("CourseID"));
            List<Map<String, Object>> course_sql_result = courseRepo.getById(course_id);
            Map<String, Object> course_info;
            CoursePreview course_preview = new CoursePreview();
            if(course_sql_result.size() > 0){
                course_info = course_sql_result.get(0);
                course_preview.setId((Long)(course_info.get("CourseID")));
                course_preview.setName(course_info.get("CourseName").toString());
                course_preview.setCredit((double)(course_info.get("Credit")));
                //course_preview.setTime(info); @todo 时间戳暂未实现
                course_preview.setClassroom(classroomRepo.getNameById((Long)(course_info.get("ClassroomID"))));
                course_preview.setTeacher(userRepository.getNameById((Long)(course_info.get("CourseTeacherID"))));              
            }else{
                response.setCode("2");
                System.out.println("[WARNING] no course found with CourseID = " + course_id.toString());
            }
            course_preview_list.add(course_preview);
        }
        response.setMessage("successfully query");
        response.setCourses(course_preview_list);
        return response;
    }

    /* 根据ID获取课程信息 */
    public GetCourseResponse getCourse(Long id){
        GetCourseResponse response = new GetCourseResponse();
        List<Map<String, Object>> sql_result = courseRepo.getById(id);
        if(sql_result.size() == 0){
            response.setCode("2");
            response.setMessage("no record");
        }else{
            if(sql_result.size() > 1){
                response.setCode("3");
                response.setMessage("more than 1 records found, send you the 1st one");
            }else{
                response.setCode("1");
                response.setMessage("successfully query");
            }
            Map<String, Object> course_info = sql_result.get(0);
            Course course = new Course();
            course.setId(id);
            course.setName(course_info.get("CourseName").toString());
            course.setCredit((double)(course_info.get("Credit")));
            course.setClassroomId((Long)(course_info.get("ClassroomID")));
            course.setClassroomName(classroomRepo.getNameById((Long)(course_info.get("ClassroomID"))));
            course.setTeacherId((Long)(course_info.get("CourseTeacherID")) + 300000000L);
            course.setTeacherName(userRepository.getNameById((Long)(course_info.get("CourseTeacherID")))); 
            course.setCapacity((Long)(course_info.get("CourseCapacity")));
            course.setSize((Long)(course_info.get("Number_of_Student_taking_Course")));
            int compulsory = (int)(course_info.get("Compulsory_or_not"));
            if(compulsory == 1){
                course.setCompulsory(true);
            }else if(compulsory == 0){
                course.setCompulsory(false);
            }
            course.setAssessmentMethod(course_info.get("AssessmentMethod").toString());
            response.setCourse(course);
        }
        return response;
    }
}
