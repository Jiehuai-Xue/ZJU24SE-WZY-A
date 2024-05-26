package com.example.demo2.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;
import com.example.demo2.dto.BasicResponse;
import com.example.demo2.dto.GetByIdRequest;
import com.example.demo2.dto.GetUserInfoResponse;
import com.example.demo2.dto.LoginRequest;
import com.example.demo2.dto.LoginResponse;
import com.example.demo2.dto.ModifyUserRequest;
import com.example.demo2.entity.User;
import com.example.demo2.service.UserService;


@RestController  
@RequestMapping("/") 
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")  
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {   // ?是通配符，表示未知类型
        System.out.println("\nget a login request...");
        LoginResponse response = userService.authenticate(request.getId(), request.getPassword());  
        // 可以选择在这里返回JWT token或其他认证凭据  
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }

    @PostMapping("get_info")
    public ResponseEntity<?> getInfo(@RequestBody GetByIdRequest request) {
        System.out.println("get a get_info request...");
        GetUserInfoResponse response = userService.getUserById(request.getOid());
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }

    @PostMapping("modify_info")
    public ResponseEntity<?> modifyInfo(@RequestBody ModifyUserRequest request) {
        System.out.println("get a modify_info request...");
        User user = new User();
        user.setEmail(request.getEmail());
        BasicResponse response = userService.modifyUserById(request.getUid(), request.getOid(), user);
        return new ResponseEntity<>(response, HttpStatus.OK);  

    }

    @PostMapping("upload")
    public ResponseEntity<?> changePhoto(@RequestParam("id") Long id, @RequestParam("photo") MultipartFile photo) {
        System.out.println("get a upload request...");
        /* update phoito file */
        if(photo.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("文件上传成功");
        }
        try {
            String uploadDir = System.getProperty("user.dir") + "/src/resources/static/user-profile/";
            System.out.println("dir: " + uploadDir);
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            id = id / 100000000L;
            File dest = new File(uploadDir + id.toString());
            photo.transferTo(dest);
            return ResponseEntity.status(HttpStatus.OK).body("文件上传成功：" + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
        }
    }

}
