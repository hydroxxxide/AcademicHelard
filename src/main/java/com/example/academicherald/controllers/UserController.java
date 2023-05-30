package com.example.academicherald.controllers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.models.User;
import com.example.academicherald.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto userDto) {
        User user = mapper.convertToEntity(userDto);
        User createdUser = userService.create(user);
        return mapper.convertToDTO(createdUser);
    }

    @GetMapping("/get/{id}")
    public UserDto getById(@PathVariable Long id) {
        return mapper.convertToDTO(userService.getById(id));
    }

    @GetMapping("/get/all")
    public List<UserDto> getAll() {
        return mapper.convertToDTOList(userService.getAll());
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody User newUser) {
        return mapper.convertToDTO(userService.update(newUser));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
    @GetMapping("/getAllGuests")
    public List<UserDto> getAllGuests(){
        return mapper.convertToDTOList(userService.getAllGuests());
    }
    @GetMapping("/getAllStudents")
    public List<UserDto> getAllStudent(){
        return mapper.convertToDTOList(userService.getAllStudent());
    }
    @GetMapping("/getAllMentors")
    public List<UserDto> getAllMentors(){
        return mapper.convertToDTOList(userService.getAllMentors());
    }
    @GetMapping("/getAllReviewer")
    public List<UserDto> getAllReviewer(){
        return mapper.convertToDTOList(userService.getAllReviewer());
    }
    @GetMapping("/getAllManager")
    public List<UserDto> getAllManager(){
        return mapper.convertToDTOList(userService.getAllManager());
    }

}
