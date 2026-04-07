package com.Home_Energy_Tracker.User_Service.controller;

import com.Home_Energy_Tracker.User_Service.dto.UserDto;
import com.Home_Energy_Tracker.User_Service.exception.ResourceNotFoundException;
import com.Home_Energy_Tracker.User_Service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "User API- RestController", description = "User API - Operations related to Home Energy Tracking System [Create, Read, Update, Delete,Sign In, Sign Out]")
public class UserController {
    private final UserService userService;

    //List of all users
    @Operation(summary = "Get all users", description = "Get paginated and sorted list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // get a single User
    @Operation(summary = "Get user by ID", description = "Fetch a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try{
            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Register user", description = "Register user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the User"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    // update User
    @Operation(summary = "Update an existing user", description = "Update user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the user"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody Long id,@RequestBody UserDto userDto) {
        try{
            UserDto user = userService.updateUser(id, userDto);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete User
    @Operation(summary = "Delete a user", description = "Delete a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the user"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
