package com.aspen.BlogApp.ServiceImp;

import com.aspen.BlogApp.dto.UserDto;
import com.aspen.BlogApp.model.User;
import com.aspen.BlogApp.repo.UserRepo;
import com.aspen.BlogApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServImp implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto){
        User user = DtoToUser(userDto);
        User saveUser = userRepo.save(user);
        return this.userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer userId){
        User user = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: "+userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        User user1 = userRepo.save(user);
        UserDto userDto1 = userToDto(user1);
        return userDto1;
    }

    @Override
    public List<UserDto> getAllUser(){
        List<User> users = userRepo.findAll();
        List<UserDto> userDto1 = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDto1;
    }


    @Override
    public UserDto getUserById(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: "+userId));

        return this.userToDto(user);
    }
    @Override
    public void deleteUser(int userId){
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+userId));
        this.userRepo.delete(user);
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        return userDto;
    }


    public  User DtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        return user;
    }

}


