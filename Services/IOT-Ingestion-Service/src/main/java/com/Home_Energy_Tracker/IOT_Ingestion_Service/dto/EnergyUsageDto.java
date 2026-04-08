package com.Home_Energy_Tracker.IOT_Ingestion_Service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyUsageDto {
    private Long deviceId;
    private double energyConsumed;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant timestamp;
}