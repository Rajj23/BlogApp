package com.aspen.BlogApp.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private int id;
    private String name;
    private String email;
    private String about;
    private String password;

}
