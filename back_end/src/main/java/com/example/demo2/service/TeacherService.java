package com.example.demo2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo2.dto.GetCoursesResponse;
import com.example.demo2.entity.CoursePreview;
import com.example.demo2.source.ClassroomRepository;
import com.example.demo2.source.CourseRepository;
import com.example.demo2.source.TeacherRepository;
import com.example.demo2.source.UserRepository;

@Service
public class TeacherService {
    @Autowired UserRepository userRepository;
    @Autowired TeacherRepository teacherRepo;
    @Autowired private CourseRepository courseRepo;
    @Autowired private ClassroomRepository classroomRepo;

    /* 教师查询自己的授课列表 */
    public GetCoursesResponse showCourses(Long id){
        GetCoursesResponse response = new GetCoursesResponse();
        response.setCode("1");
        List<Map<String, Object>> sql_result = userRepository.getTeachesById(id);
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
}
