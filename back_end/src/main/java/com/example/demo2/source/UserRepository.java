package com.example.demo2.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.example.demo2.entity.User;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate; // 在类中注入 JdbcTemplate实例
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("user")
            .usingGeneratedKeyColumns("UserID");
    }
    
    /* 添加用户，并返回ID */
    public Long add(User user){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("UserID", null);
        parameters.put("Password", user.getPassword());
        parameters.put("Name", user.getName());
        parameters.put("Status", user.getStatus());        
        parameters.put("Gender", user.getGender());
        parameters.put("Age", user.getAge());
        parameters.put("Phone", user.getPhone());
        parameters.put("Email", user.getEmail());
        parameters.put("Address", user.getAddress());
        Number generated_id = simpleJdbcInsert.executeAndReturnKey(parameters);
        return generated_id.longValue();
    }
    
    /* 删除用户 */
    public void deleteUser(Long id){
        String sql = "DELETE FROM user WHERE UserID = ?";
        jdbcTemplate.update(sql, id % 100000000L);
    }

    /* 根据ID获取密码 */
    public String getPasswordById(Long id){
        try{
            String password = "";
            System.out.println("正在查询...");
            String sql = "SELECT Password FROM user WHERE UserID = ?";
            password = jdbcTemplate.queryForObject(sql, String.class, Long.toString(id % 100000000L));
            System.out.println("查询成功... password = " + password);
            return password;
        }catch(EmptyResultDataAccessException e){
            return "";
        }
    }

    /* 根据ID获取姓名 */
    public String getNameById(Long id){
        try{
            String name = "";
            String sql = "SELECT Name FROM user WHERE UserID = ?";
            name = jdbcTemplate.queryForObject(sql, String.class, Long.toString(id % 100000000L));
            return name;
        }catch(EmptyResultDataAccessException e){
            return "";
        }
    }

    /* 根据Id来查询用户的缩略版信息 */
    public List<Map<String, Object>> getUserPreviewById(Long id){
        String sql = "SELECT UserID, Name, Gender, Age FROM user WHERE UserID = " + Long.toString(id % 100000000L);
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 根据Id来查询用户的详细信息 */
    public List<Map<String, Object>> getUserById(Long id){
        String sql = "SELECT * FROM user WHERE UserID = " + Long.toString(id % 100000000L);
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 根据学生ID来查询其选课表 */
    public List<Map<String, Object>> getCurriculumById(Long id){
        String sql = "SELECT CourseID FROM student_select_course WHERE StudentID = " + Long.toString(id % 100000000L);
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 根据教师ID来查询其授课表 */
    public List<Map<String, Object>> getTeachesById(Long id){
        String sql = "SELECT CourseID FROM teacher_teach_course WHERE TeacherID = " + Long.toString(id % 100000000L);
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 根据Id来修改用户的信息 */
    public int modifyUserById(Long Id, User user){
        String sql = "UPDATE user SET Email = ? WHERE UserID = ?";
        int sql_result = jdbcTemplate.update(sql, user.getEmail(), Id % 100000000L);
        return sql_result;
    }

    /* 默认查询前20个用户 */
    public List<Map<String, Object>> getUserPreviewsByDefault(){
        String sql = "SELECT UserID, Name, Status, Gender FROM user ORDER BY UserID DESC LIMIT 20";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 根据姓名来查询用户，由于用户可能会重名，所以返回的是一个List<User> */
    public List<Map<String, Object>> getUserPreviewsByName(String name){
        String sql = "SELECT UserID, Name, Status, Gender FROM user WHERE Name = '" + name + "'";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }

    /* 筛选某一性别的用户 */
    public List<Map<String, Object>> getUserPreviewsByGender(String gender){
        String sql = "SELECT UserID, Name, Status,Gender FROM user WHERE Gender = '" + gender + "'";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }
}
