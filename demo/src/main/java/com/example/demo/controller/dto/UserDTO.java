package com.example.demo.controller.dto;

import lombok.Data;

/**
 * 接收前端数据
 */
@Data
public class UserDTO {

    private String name;
    private String password;
    private String nick;

}
