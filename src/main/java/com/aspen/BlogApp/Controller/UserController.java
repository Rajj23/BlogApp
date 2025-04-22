package com.aspen.BlogApp.Controller;

import com.aspen.BlogApp.dto.UserDto;
import com.aspen.BlogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<UserDto> createUserController(@RequestBody UserDto user){
        UserDto user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping("user/{userId}")
    public ResponseEntity<UserDto> updateUserData(@RequestBody UserDto user,@PathVariable("userId") int id){
        UserDto userDto = userService.updateUser(user,id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUserInController(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserDto> getUserByIdInController(@PathVariable("userId") int userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}
