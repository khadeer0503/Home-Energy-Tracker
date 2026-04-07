package com.Home_Energy_Tracker.Device_Service.controller;

import com.Home_Energy_Tracker.Device_Service.dto.DeviceDto;
import com.Home_Energy_Tracker.Device_Service.exception.ResourceNotFoundException;
import com.Home_Energy_Tracker.Device_Service.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private final DeviceService deviceService;

    //List of all devices
    @Operation(summary = "Get all devices", description = "Get paginated and sorted list of devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of devices"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping
    public ResponseEntity<List<DeviceDto>> getAllDevices() {
        try {
            List<DeviceDto> devices = deviceService.getAllDevices();
            return ResponseEntity.ok(devices);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Devices not found");
        }
    }

    // get a single device
    @Operation(summary = "Get device by ID", description = "Fetch a device by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the device"),
            @ApiResponse(responseCode = "404", description = "device not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long id) {
        DeviceDto device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @Operation(summary = "Register device", description = "Register device in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the device"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto device) {
        DeviceDto deviceDto = deviceService.createDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceDto);
    }

    // update device
    @Operation(summary = "Update an existing device", description = "Update device details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the device"),
            @ApiResponse(responseCode = "404", description = "device not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto) {
        DeviceDto updateDevice = deviceService.updateDevice(id, deviceDto);
        return ResponseEntity.ok(updateDevice);
    }

    // Delete device
    @Operation(summary = "Delete a device", description = "Delete a device by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the device"),
            @ApiResponse(responseCode = "404", description = "device not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceDto> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }


    //List of all devices of the User By its ID
    @Operation(summary = "Get all devices of the User", description = "Get paginated and sorted list of devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of devices of the user"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/device/{userId}")
    public ResponseEntity<List<DeviceDto>> getAllDevicesByUserId(@PathVariable Long userId) {
        List<DeviceDto> devices = deviceService.getAllDevicesByUserId(userId);
        return ResponseEntity.ok(devices);
    }

}
