package com.example.demo2.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Long id){
        String sql = "INSERT INTO teacher VALUES (" + Long.toString(id % 100000000L) + ", null)";
        jdbcTemplate.update(sql);
    }
}
