package com.example.demo2.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.dto.BasicResponse;
import com.example.demo2.dto.GetLogsResponse;
import com.example.demo2.source.LogRepository;
import com.example.demo2.source.StudentRepository;
import com.example.demo2.source.TeacherRepository;
import com.example.demo2.source.UserRepository;
import com.example.demo2.entity.Log;
import com.example.demo2.entity.LogView;
import com.example.demo2.entity.User;

@Service
public class SysAdminService {

    @Autowired private UserRepository userRepository; 
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private LogRepository logRepository;

    /* 添加系统管理员 */
    public BasicResponse addUser(Long uid, User user){
        BasicResponse response = new BasicResponse();
        if(! user.getPassword().equals("")){
            System.out.println("[WARNING] password != empty");
        }
        if(user.getStatus().equals("SA")){
            response.setCode("-1");
            response.setMessage("sys_admin cannot add another sys_admin");
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

    /* 查询最近的20条日志 */
    public GetLogsResponse getLogs(){
        GetLogsResponse response = new GetLogsResponse();
        List<Map<String, Object>> sql_result = logRepository.get();
        ArrayList<LogView> logs = new ArrayList<LogView>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for(Map<String, Object> line : sql_result){
            LogView log = new LogView((Long)(line.get("UserID")), ((LocalDateTime)(line.get("OperationTime"))).format(formatter), line.get("LogContent").toString());
            logs.add(log);
        }
        response.setCode("1");
        response.setCode("successfully query");
        response.setLogs(logs);
        return response;
    }
}
