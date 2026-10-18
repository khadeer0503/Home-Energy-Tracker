package com.Home_Energy_Tracker.Insight_Service.dto;

import lombok.Builder;

@Builder
public record InsightDto(
        Long userId,
        String tips,
        double energyUsage
) {
}
