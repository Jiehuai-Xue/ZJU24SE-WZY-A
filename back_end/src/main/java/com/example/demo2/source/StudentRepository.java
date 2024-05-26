package com.example.demo2.source;

import com.example.demo2.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

@Repository // 标注数据访问组件JdbcTemplate
public class StudentRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate; // 在类中注入 JdbcTemplate实例

    public void add(Long id){
        String sql = "INSERT INTO student VALUES (" + Long.toString(id % 100000000L) + ", null, '未设置')";
        jdbcTemplate.update(sql);
    }

    public List<Map<String, Object>> ShowAll(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from student");
        System.out.println(list.size());
        return list;
    }

}
