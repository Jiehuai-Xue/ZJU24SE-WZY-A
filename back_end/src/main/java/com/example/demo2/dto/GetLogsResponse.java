package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

import com.example.demo2.entity.LogView;

@Getter@Setter
public class GetLogsResponse{
    private String code = "";
    private String message = "";
    private ArrayList<LogView> logs;

    public void setLogs(ArrayList<LogView> logs){
        this.logs = logs;
    }
}
