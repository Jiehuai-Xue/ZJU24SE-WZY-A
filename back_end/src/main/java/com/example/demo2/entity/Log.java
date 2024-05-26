package com.example.demo2.entity;

import java.sql.Timestamp;
import java.lang.System;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Log {
    private Long id = 0L;
    private Long uid = 0L;
    private Timestamp time = new Timestamp(System.currentTimeMillis());
    private String content = "";

    public Log(Long uid, Timestamp time, String content){
        this.uid = uid;
        this.time = time;
        this.content = content;
    }
}
