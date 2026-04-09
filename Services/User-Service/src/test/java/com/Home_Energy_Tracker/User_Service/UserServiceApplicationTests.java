package com.Home_Energy_Tracker.User_Service;

import com.Home_Energy_Tracker.User_Service.entity.User;
import com.Home_Energy_Tracker.User_Service.repository.UserRepository;
import com.Home_Energy_Tracker.User_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@Slf4j
@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private UserRepository userRepository;
	private final Random random = new Random();

	private static final int NUMBER_OF_USERS = 20;

	@Test
	void contextLoads() {
	}

	@Test
	void addUserToDB(){
		for(int i=1;i<NUMBER_OF_USERS;i++){
			User user = User.builder()
					.name("User"+i)
					.surname("Surname"+i)
					.email("user_"+i+"@Devtest.com")
					.address("User Address"+i)
					.alerting(i % 2 == 0) // Alternate alerting preference
					.energyAlertingThreshold(Math.round((random.nextDouble() * 100.0) * 100.0) / 100.0) // Random threshold between 0 and 100 kWh
					.build();
			User savedUser = userRepository.save(user);
			log.info("User Repository has been populated");
		}
	}

}
