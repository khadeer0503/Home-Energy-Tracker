package com.Home_Energy_Tracker.IOT_Ingestion_Service.service.INGESTION_IMPL;

import com.Home_Energy_Tracker.IOT_Ingestion_Service.dto.EnergyUsageDto;
import com.Home_Energy_Tracker.IOT_Ingestion_Service.service.IngestionService;
import com.Home_Energy_Tracker.kafka.event.EnergyUsageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngestionServiceImpl implements IngestionService {

    private final ModelMapper modelMapper;
    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    @Override
    public void ingestEnergyUsage(EnergyUsageDto input) {
        EnergyUsageDto event = EnergyUsageDto.builder()
                .deviceId(input.getDeviceId())
                .energyConsumed(input.getEnergyConsumed())
                .timestamp(input.getTimestamp())
                .build();
        kafkaTemplate.send("energy-usage", modelMapper.map(event, EnergyUsageEvent.class));
        log.info("Energy Usage Event sent to topic : {}", event);


    }
}
