package com.aspen.BlogApp.service;

import com.aspen.BlogApp.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(int userId);
    List<UserDto> getAllUser();
    void deleteUser(int userId);

}
