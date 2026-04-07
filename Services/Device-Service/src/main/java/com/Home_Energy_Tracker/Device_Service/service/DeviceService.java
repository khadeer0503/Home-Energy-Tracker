package com.Home_Energy_Tracker.Device_Service.service;

import com.Home_Energy_Tracker.Device_Service.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    DeviceDto getDeviceById(Long id);
    DeviceDto createDevice(DeviceDto input);
    DeviceDto updateDevice(Long id,DeviceDto input);
    void deleteDevice(Long id);
    List<DeviceDto> getAllDevices();
    public List<DeviceDto> getAllDevicesByUserId(Long userId);

}
