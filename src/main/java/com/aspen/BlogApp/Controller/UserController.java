package com.aspen.BlogApp.Controller;

import com.aspen.BlogApp.dto.UserRequestDto;
import com.aspen.BlogApp.dto.UserResponseDto;
import com.aspen.BlogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<UserResponseDto> createUserController(@RequestBody UserRequestDto user){
        UserResponseDto user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<UserResponseDto> updateUserData(@RequestBody UserRequestDto user, @PathVariable("userId") int id){
        UserResponseDto userResponseDto = userService.updateUser(user,id);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto>> getAllUserInController(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponseDto> getUserByIdInController(@PathVariable("userId") int userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<String> deleteUserController(@PathVariable("userId") int userId){
        if (userService.getUserById(userId)==null){
            return new ResponseEntity<>("User not found with this id",HttpStatus.BAD_REQUEST);
        }
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
    }

}
