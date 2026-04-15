package com.Home_Energy_Tracker.Energy_Usage_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDto {
    Long id;
    String name;
    String type;
    String location;
    Long userId;
    Double energyConsumed;
}
