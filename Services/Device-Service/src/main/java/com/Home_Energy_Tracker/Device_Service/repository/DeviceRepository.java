package com.Home_Energy_Tracker.Device_Service.repository;

import com.Home_Energy_Tracker.Device_Service.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
   // List<Device> findByDeviceId(Long id);

    List<Device> findAllByUserId(Long userId);
}
