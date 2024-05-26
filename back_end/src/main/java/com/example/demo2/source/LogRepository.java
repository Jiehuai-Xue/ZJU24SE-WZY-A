package com.example.demo2.source;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo2.entity.Log;

@Repository
public class LogRepository {
    
    @Autowired private JdbcTemplate jdbcTemplate;

    /* 添加一条日志 */
    public void add(Log log){
        String sql = "INSERT INTO log VALUES (null, ?, ?, ?)";
        jdbcTemplate.update(sql, Long.toString(log.getUid() % 100000000L), log.getTime(), log.getContent());
    }

    /* 日志不允许删除 */

    /* 查询最新的20日志 */
    public List<Map<String, Object>> get(){
        String sql = "SELECT * FROM log ORDER BY OperationTime DESC LIMIT 20";
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }    
    
    /* 按照用户ID查询日志 */
    public List<Map<String, Object>> getByUid(Long uid){
        String sql = "SELECT * FROM log WHERE UserID = " + Long.toString(uid % 100000000L);
        List<Map<String, Object>> sql_result = jdbcTemplate.queryForList(sql);
        return sql_result;
    }


    /* 按照时间查询日志 */
    // @todo
    
    /* 日志不允许修改 */

}
