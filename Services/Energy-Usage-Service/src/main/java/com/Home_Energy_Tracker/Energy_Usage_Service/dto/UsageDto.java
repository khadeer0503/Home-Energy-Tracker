package com.Home_Energy_Tracker.Energy_Usage_Service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsageDto {
    Long userId;
    List<DeviceDto> devices;
}
