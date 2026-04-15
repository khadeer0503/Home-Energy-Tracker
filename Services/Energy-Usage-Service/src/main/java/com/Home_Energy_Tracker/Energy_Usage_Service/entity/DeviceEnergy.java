package com.Home_Energy_Tracker.Energy_Usage_Service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceEnergy {
    private Long deviceId;
    private double energyConsumed;
    private Long userId;
}
