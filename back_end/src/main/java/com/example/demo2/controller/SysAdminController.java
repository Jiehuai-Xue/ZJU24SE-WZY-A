package com.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo2.dto.GetLogsResponse;
import com.example.demo2.dto.GetRequest;
import com.example.demo2.dto.AddUserRequest;
import com.example.demo2.dto.BasicResponse;
import com.example.demo2.service.SysAdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://http://127.0.0.1/:5173")
@RestController  
@RequestMapping("/sys_admin")  
public class SysAdminController {
    private final SysAdminService sysAdminService;
  
    @Autowired   // 尝试自动装配该bean的依赖项
    public SysAdminController(SysAdminService sysAdminService) {  
        this.sysAdminService = sysAdminService;  
    } 

    /* 添加用户 */
    @PostMapping("/user_management/add")
    public BasicResponse addUser(@RequestBody AddUserRequest request) {
        System.out.println("\nget a sys_admin/user_management/add request...");
        BasicResponse response = sysAdminService.addUser(request.getId(), request.getUser());
        return response;
    }

    /* 查看日志 */
    @PostMapping("/log")
    public GetLogsResponse postMethodName(@RequestBody GetRequest request) {
        System.out.println("\nget a /sys_admin/log request...");
        GetLogsResponse response = sysAdminService.getLogs();
        return response;
    }
    
    
}
