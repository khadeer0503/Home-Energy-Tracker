package com.Home_Energy_Tracker.Energy_Usage_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnergyUsageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyUsageServiceApplication.class, args);
	}

}
