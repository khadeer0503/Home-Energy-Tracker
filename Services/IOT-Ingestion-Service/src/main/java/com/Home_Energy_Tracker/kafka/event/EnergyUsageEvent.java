package com.Home_Energy_Tracker.kafka.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnergyUsageEvent {
    private Long deviceId;
    private double energyConsumed;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant timestamp;

}
