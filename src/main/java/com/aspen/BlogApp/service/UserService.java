package com.aspen.BlogApp.service;

import com.aspen.BlogApp.dto.UserRequestDto;
import com.aspen.BlogApp.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto user);
    UserResponseDto updateUser(UserRequestDto user, Integer userId);
    UserResponseDto getUserById(int userId);
    List<UserResponseDto> getAllUser();
    void deleteUser(int userId);

}
