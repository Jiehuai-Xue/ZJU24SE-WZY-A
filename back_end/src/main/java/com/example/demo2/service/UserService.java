package com.example.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.example.demo2.dto.GetUserInfoResponse;
import com.example.demo2.dto.LoginResponse;
import com.example.demo2.entity.Log;
import com.example.demo2.entity.User;
import com.example.demo2.source.LogRepository;
import com.example.demo2.source.UserRepository;
import com.example.demo2.types.Privilege;
import com.example.demo2.dto.BasicResponse;

@Service
public class UserService {
    
    @Autowired private UserRepository userRepository; 
    @Autowired private LogRepository logRepo;

    /* 用户通过密码登录验证 */
    public LoginResponse authenticate(Long id, String password) {  
        System.out.println("id = " + id + ", passward = " + password);
        LoginResponse response = new LoginResponse(); 
        Long quotient = Long.valueOf(id / 100000000L);
        String expected_password = "";
        /* 查询数据库来获取用户名和密码 */
        if(quotient >= 1 && quotient <= 4){
            expected_password = userRepository.getPasswordById(id);
        }else{
            response.setCode("-2");
            response.setMessage("invalid id");
            System.out.println("invalid id");   
            return response;         
        }
        if(expected_password.equals("")){
            response.setCode("-2");
            response.setMessage("invalid id");
            System.out.println("invalid id");            
            return response;
        }else if (expected_password.equals(password)) {  
            response.setCode("1");
            response.setMessage("login successfully");
            if(quotient == 1){
                response.setPrivilege(Privilege.SYS_ADMIN);
                System.out.println("sys_admin");
            }else if(quotient == 2){
                response.setPrivilege(Privilege.ADMIN);
                System.out.println("admin");
            }else if(quotient == 3){
                response.setPrivilege(Privilege.T);
                System.out.println("teacher");
            }else if(quotient == 4){
                response.setPrivilege(Privilege.S);
                System.out.println("student");
            }
            /* 在日志中添加信息 */
            logRepo.add(new Log(id, new Timestamp(System.currentTimeMillis()), "log in"));
        } else {
            response.setCode("-1");
            response.setMessage("password is incorrect");
        }  
        return response;
    }  

    /* 用户查看自己的基本信息 */
    public GetUserInfoResponse getUserById(Long id){
        GetUserInfoResponse response = new GetUserInfoResponse();
        User user = new User();
        List<Map<String, Object>> sql_result = userRepository.getUserById(id);
        if(sql_result.size() == 0){
            response.setCode("-1");
            response.setMessage("user does not exit");
            response.setData(user);
            System.out.println("[ERROR] sql result = 0");
            return response;
        }else{
            Map<String, Object> info = sql_result.get(0);
            user.setId(id);
            user.setName(info.get("Name").toString());
            String status = info.get("Status").toString();
            if(status.equals("SA")){
                user.setStatus("系统管理员");
            }else if(status.equals("A")){
                user.setStatus("管理员");
            }else if(status.equals("T")){
                user.setStatus("教师");
            }else if(status.equals("S")){
                user.setStatus("学生");
            }
            user.setGender(info.get("Gender").toString());
            user.setAge(((Long)(info.get("Age"))).intValue());
            user.setPhone(info.get("Phone").toString());
            user.setEmail(info.get("Email").toString());
            user.setAddress(info.get("Address").toString());

            if(sql_result.size() == 1){
                response.setCode("1");
                response.setMessage("successfully query");                
            }else{
                response.setCode("2");
                response.setMessage("there are " + sql_result + " records with this id, the back end send you the 1st one");
            }
            response.setData(user);
            logRepo.add(new Log(id, new Timestamp(System.currentTimeMillis()), "get personal information"));
            return response;            
        }

    }

    /* 用户修改自己的基本信息 */
    public BasicResponse modifyUserById(Long uid, Long oid, User user){
        BasicResponse response = new BasicResponse();
        int sql_result = userRepository.modifyUserById(oid, user);
        response.setCode("1");
        response.setMessage("successfully update");
        logRepo.add(new Log(uid, new Timestamp(System.currentTimeMillis()), "modify personal information"));
        return response;
    }
}
