package com.example.demo2.dto;

import com.example.demo2.types.Privilege;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String code= "";  
    private String message = "";  
    private Privilege privilege = Privilege.NOT_SET; 
  
    // constructors
    public LoginResponse() {  
    }  
    public LoginResponse(String code, String message) {  
        this.code = code;  
        this.message = message;
    }  

    // for debug
    public String toString(){return "code:" + code + "message:" + message + "privilage:" + privilege;}
}
