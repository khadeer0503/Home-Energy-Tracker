package com.Home_Energy_Tracker.IOT_Ingestion_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IotIngestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotIngestionServiceApplication.class, args);
	}

}
