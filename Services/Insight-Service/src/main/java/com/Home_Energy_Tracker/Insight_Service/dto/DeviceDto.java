package com.Home_Energy_Tracker.Insight_Service.dto;

import lombok.Builder;

@Builder
public record DeviceDto(
        Long id,
        String name,
        String type,
        String location,
        double energyConsumed
) {
}
