package com.Home_Energy_Tracker.Device_Service;

import com.Home_Energy_Tracker.Device_Service.entity.Device;
import com.Home_Energy_Tracker.Device_Service.entity.DeviceType;
import com.Home_Energy_Tracker.Device_Service.repository.DeviceRepository;
import com.Home_Energy_Tracker.User_Service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DeviceServiceApplicationTests {

	@Autowired
	private DeviceRepository deviceRepository;
	private static final int NUMBER_OF_DEVICES = 50;
	private static final int USERS = 10;


	@Test
	void contextLoads() {
	}

	@Test
	void createDevicesTest() {

		for (int i = 1; i < NUMBER_OF_DEVICES; i++) {
			Device device = Device.builder()
					.name("Device" + i)
					.type(DeviceType.values()[i % DeviceType.values().length]) // Cycle through device types
					.userId((long) ((i % USERS) + 1))
					.location("Location" + ((i % 3) + 1))
					.build();
			Device savedDevice = deviceRepository.save(device);
			log.info("Device Repository has been populated");
		}

	}

}
