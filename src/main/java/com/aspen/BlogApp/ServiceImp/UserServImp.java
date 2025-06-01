package com.aspen.BlogApp.ServiceImp;

import com.aspen.BlogApp.dto.UserRequestDto;
import com.aspen.BlogApp.dto.UserResponseDto;
import com.aspen.BlogApp.model.User;
import com.aspen.BlogApp.repo.UserRepo;
import com.aspen.BlogApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServImp implements UserService{

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto userDto){
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = DtoToUser(userDto);
        User saveUser = userRepo.save(user);
        return this.userToDto(saveUser);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto, Integer userId){
        User user = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: "+userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        User user1 = userRepo.save(user);
        UserResponseDto userResponseDto1 = userToDto(user1);
        return userResponseDto1;
    }

    @Override
    public List<UserResponseDto> getAllUser(){
        List<User> users = userRepo.findAll();
        List<UserResponseDto> userResponseDto1 = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userResponseDto1;
    }


    @Override
    public UserResponseDto getUserById(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: "+userId));

        return this.userToDto(user);
    }
    @Override
    public void deleteUser(int userId){
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+userId));
        this.userRepo.delete(user);
    }

    public UserResponseDto userToDto(User user){
        UserResponseDto userResponseDto = this.modelMapper.map(user, UserResponseDto.class);

        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setAbout(user.getAbout());
        return userResponseDto;
    }


    public  User DtoToUser(UserRequestDto userResponseDto){
        User user = new User();
        user.setId(userResponseDto.getId());
        user.setName(userResponseDto.getName());
        user.setEmail(userResponseDto.getEmail());
        user.setPassword(userResponseDto.getPassword());
        user.setAbout(userResponseDto.getAbout());
        return user;
    }

}


