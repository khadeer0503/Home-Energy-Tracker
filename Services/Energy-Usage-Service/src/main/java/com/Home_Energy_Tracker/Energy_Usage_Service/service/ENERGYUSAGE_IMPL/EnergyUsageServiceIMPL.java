package com.Home_Energy_Tracker.Energy_Usage_Service.service.ENERGYUSAGE_IMPL;

import com.Home_Energy_Tracker.Energy_Usage_Service.service.EnergyUsageService;
import com.Home_Energy_Tracker.Kafka.event.EnergyUsageEvent;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnergyUsageServiceIMPL implements EnergyUsageService {

    private final InfluxDBClient influxDBClient;

    @Value("${influx.bucket}")
    private String influxBucket;

    @Value("${influx.org}")
    private String influxOrg;


    @Override
    @KafkaListener(topics = "energy-usage", groupId = "energy-usage-consumer-group")
    public void energyUsageEvent(EnergyUsageEvent energyUsageEvent) {
        Point point = Point.measurement("energy_usage")
                .addTag("deviceId", String.valueOf(energyUsageEvent.getDeviceId()))
                .addField("energyConsumed", energyUsageEvent.getEnergyConsumed())
                .time(energyUsageEvent.getTimestamp(), WritePrecision.MS);
        influxDBClient.getWriteApiBlocking().writePoint(influxBucket, influxOrg, point);

    }

    @Override
    public void aggregateDeviceEnergyUsage() {

    }
}
