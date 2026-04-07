package com.Home_Energy_Tracker.User_Service.service.USER_IMPL;

import com.Home_Energy_Tracker.User_Service.dto.UserDto;
import com.Home_Energy_Tracker.User_Service.entity.User;
import com.Home_Energy_Tracker.User_Service.exception.ResourceNotFoundException;
import com.Home_Energy_Tracker.User_Service.repository.UserRepository;
import com.Home_Energy_Tracker.User_Service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto input) {
        User createduser = User.builder()
                .name(input.getName())
                .surname(input.getSurname())
                .email(input.getEmail())
                .address(input.getAddress())
                .alerting(input.isAlerting())
                .energyAlertingThreshold(input.getEnergyAlertingThreshold())
                .build();
        User userSaved = userRepository.save(createduser);
        return modelMapper.map(userSaved, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("user not found","id","id"));
    }

    @Override
    public UserDto updateUser(Long id, UserDto input) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found", "id", "id"));
        User updatedUser = User.builder()
                .name(input.getName())
                .surname(input.getSurname())
                .email(input.getEmail())
                .address(input.getAddress())
                .alerting(input.isAlerting())
                .energyAlertingThreshold(input.getEnergyAlertingThreshold())
                .build();
        User savedUpdatedUser = userRepository.save(user);
        return modelMapper.map(savedUpdatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found", "id", "id"));
        userRepository.delete(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

}
