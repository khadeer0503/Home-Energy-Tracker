package com.Home_Energy_Tracker.User_Service.service;

import com.Home_Energy_Tracker.User_Service.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto input);
     UserDto getUserById(Long id);
     List<UserDto> getAllUsers();
     UserDto updateUser(Long id, UserDto input);
     void deleteUser(Long id);
}
