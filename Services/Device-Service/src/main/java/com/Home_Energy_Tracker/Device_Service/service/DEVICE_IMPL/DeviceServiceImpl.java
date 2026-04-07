package com.Home_Energy_Tracker.Device_Service.service.DEVICE_IMPL;

import com.Home_Energy_Tracker.Device_Service.dto.DeviceDto;
import com.Home_Energy_Tracker.Device_Service.entity.Device;
import com.Home_Energy_Tracker.Device_Service.exception.ResourceNotFoundException;
import com.Home_Energy_Tracker.Device_Service.repository.DeviceRepository;
import com.Home_Energy_Tracker.Device_Service.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModelMapper modelMapper;

    @Override
    public DeviceDto getDeviceById(Long id) {
        Device deviceById = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + id));
        return modelMapper.map(deviceById, DeviceDto.class);
    }

    @Override
    public List<DeviceDto> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream()
                .map(device -> modelMapper.map(device, DeviceDto.class))
                .toList();
    }

    @Override
    public DeviceDto createDevice(DeviceDto input)
    {
        Device device = Device.builder()
                .name(input.getName())
                .type(input.getType())
                .location(input.getLocation())
                .userId(input.getUserId())
                .build();
        Device saveDevice = deviceRepository.save(device);
        return modelMapper.map(saveDevice, DeviceDto.class);
    }

    @Override
    public DeviceDto updateDevice(Long id, DeviceDto input) {
        Device deviceById = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + id));
        Device updatedDevice = Device.builder()
                .id(id)
                .name(input.getName())
                .type(input.getType())
                .location(input.getLocation())
                .userId(input.getUserId())
                .build();
        deviceRepository.save(updatedDevice);
        return modelMapper.map(updatedDevice, DeviceDto.class);
    }

    @Override
    public void deleteDevice(Long id) {
        Device deviceById = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + id));
        deviceRepository.deleteById(id);
    }

    @Override
    public List<DeviceDto> getAllDevicesByUserId(Long userId) {
        List<Device> devices = deviceRepository.findAllByUserId(userId);
        return devices.stream()
                .map(device -> modelMapper.map(device, DeviceDto.class))
                .toList();
    }
}
