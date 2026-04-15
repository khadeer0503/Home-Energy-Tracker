package com.Home_Energy_Tracker.Energy_Usage_Service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    Long id;
    String name;
    String type;
    String location;
    Long userId;
    Double energyConsumed;
}