package com.example.demo2.source;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo2.entity.Course;

import jakarta.annotation.PostConstruct;

@Repository
public class CourseRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("course")
            .usingGeneratedKeyColumns("CourseID");
    }
    
    /* 添加一门课程 */
    public Long add(Course course){
        /* insert to course table */
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CourseID", null);
        parameters.put("CourseName", course.getName());
        parameters.put("Credit", course.getCredit());
        parameters.put("CourseTime", course.getTime());
        parameters.put("ClassroomID", course.getClassroomId());
        parameters.put("CourseTeacherID", course.getTeacherId());
        parameters.put("CourseCapacity", course.getCapacity());
        parameters.put("Number_of_Student_taking_Course", 0);
        parameters.put("Compulsory_or_not", course.ifCompulsory());
        parameters.put("AssessmentMethod", course.getAssessmentMethod());
        Number course_id = simpleJdbcInsert.executeAndReturnKey(parameters);
        /* insert to teacher_teach_course table */
        String sql2 = "INSERT INTO teacher_teach_course VALUES (?, ?)";
        jdbcTemplate.update(sql2, course_id.longValue(), course.getTeacherId() % 100000000L);
        return course_id.longValue();
    }

    public int delete(Long id){
        String sql = "DELETE FROM course WHERE CourseID = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    /* 查询前20条课程信息 */
    public List<Map<String, Object>> get20(){
        String sql = "SELECT * FROM course ORDER BY CourseID ASC LIMIT 20";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;        
    }

    /* 根据id查询课程 */
    public List<Map<String, Object>> getById(Long id){
        String sql = "SELECT * FROM course WHERE CourseID = " + id.toString();
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 返回前20个课程 */
    public List<Map<String, Object>> getCoursesByDefault(){
        String sql = "SELECT CourseID, CourseName, Credit, CourseTime, ClassroomID, CourseTeacherID FROM course ORDER BY CourseID DESC LIMIT 20";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 根据名字查询课程 */
    public List<Map<String, Object>> getCoursesByName(String name){
        String sql = "SELECT CourseID, CourseName, Credit, CourseTime, ClassroomID, CourseTeacherID FROM course WHERE CourseName = '" + name + "'";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }
}
