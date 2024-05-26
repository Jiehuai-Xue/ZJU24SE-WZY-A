package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo2.entity.User;

@Getter@Setter
public class ModifyUserRequest {
    private Long uid = 0L;
    private Long oid = 0L;
    private String email;
}
