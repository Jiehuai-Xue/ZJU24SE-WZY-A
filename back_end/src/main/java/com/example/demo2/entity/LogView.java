package com.example.demo2.entity;

import java.sql.Timestamp;
import java.lang.System;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LogView {
    private Long uid = 0L;
    private String time = "";
    private String content = "";

    public LogView(Long uid, String time, String content){
        this.uid = uid;
        this.time = time;
        this.content = content;
    }
}
