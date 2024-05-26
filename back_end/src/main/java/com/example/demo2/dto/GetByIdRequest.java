package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GetByIdRequest {
    private Long uid = 0L;
    private Long oid = 0L;
}
