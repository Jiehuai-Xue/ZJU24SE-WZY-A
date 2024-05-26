package com.example.demo2.source;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo2.entity.Classroom;


@Repository
public class ClassroomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("classroom")
            .usingGeneratedKeyColumns("ClassroomID");
    }

    /* 添加教室 */
    public Long add(Classroom classroom){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ClassroomID", null);
        parameters.put("ClassroomName", classroom.getName());
        parameters.put("CampusInformation", classroom.getCampus());
        parameters.put("ClassroomCapacity", classroom.getCapacity());
        parameters.put("Special_Conditions_of_Classrooms", classroom.getCondition());
        Number generated_id = simpleJdbcInsert.executeAndReturnKey(parameters);
        return generated_id.longValue();
    }

    /* 删除教室 */
    public int delete(Long id){
        String sql = "DELETE FROM classroom WHERE ClassroomID = ?";
        jdbcTemplate.update(sql, id);
        return 1;
    }

    /* 默认查询前20个教室 */
    public List<Map<String, Object>> getByDefault(){
        String sql = "SELECT * FROM classroom ORDER BY ClassroomID DESC LIMIT 20";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;        
    }

    /* 根据ID获取教室名字 */
    public String getNameById(Long id){
        try{
            String name = "";
            String sql = "SELECT ClassroomName FROM classroom WHERE ClassroomID = ?";
            name = jdbcTemplate.queryForObject(sql, String.class, id.toString());
            return name;
        }catch(EmptyResultDataAccessException e){
            return "";
        }
    }
}
