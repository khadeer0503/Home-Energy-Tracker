package com.Home_Energy_Tracker.IOT_Ingestion_Service.simulation;

import com.Home_Energy_Tracker.IOT_Ingestion_Service.dto.EnergyUsageDto;
import com.Home_Energy_Tracker.IOT_Ingestion_Service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
//@Component
public class ContinuousDataSimulator implements CommandLineRunner {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    @Value("${simulation.interval-ms}")
    private int requestPerMs;

    @Value("${simulation.request-per-interval:100}")
    private int requestPerInterval;

    @Value("${simulation.endpoint}")
    private String ingestionEndpoint;

    @Override
    public void run(String... args) throws Exception {
        log.info("Continuous Data Simulator started...");
    }

    /* we are sending the Continuous Data for Simulator to the Ingestion Service and then this service will send it to Kafka.
      As generating manually by hand with RestApi is difficult.So we are generating in High number as Kafka can process it.
      We can see this in Kafka UI on Port:8070 which we mention in docker-compose then select the Topic which is energy-usage.

     PROBLEM: THE DATA THAT IS PRODUCING IS A SINGLE THREAD. SO TO ACHIEVE MULTITHREAD DATA WE CREATED ParallelDataSimulator CLASS.

     */

    //@Scheduled(fixedRateString = "${simulation.interval-ms}") // Send data every  millisecond
    public void sendMockData() {

        for (int i = 0; i < requestPerInterval; i++) {
            EnergyUsageDto energyUsageDto = EnergyUsageDto.builder()
                    .deviceId(random.nextLong(10)+1)
                    .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                    .energyConsumed(Math.round(random.nextDouble(0.0, 2.0) * 100.0) / 100.0)
                   // .energyConsumed(random.nextDouble() * 10) // Random energy consumption between 0 and 10 kWh
                    .build();

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<EnergyUsageDto> energyUsageDtoHttpEntity = new HttpEntity<>(energyUsageDto, headers);
                restTemplate.postForEntity(ingestionEndpoint, energyUsageDtoHttpEntity, Void.class);

            } catch (Exception e) {
                log.error("Failed to send mock data to {}", ingestionEndpoint, e);
            }
        }
    }
}
