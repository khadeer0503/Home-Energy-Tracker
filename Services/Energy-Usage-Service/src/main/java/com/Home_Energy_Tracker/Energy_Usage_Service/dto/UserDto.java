package com.Home_Energy_Tracker.Energy_Usage_Service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    Long id;
    String name;
    String surname;
    String email;
    String address;
    boolean alerting;
    double energyAlertingThreshold;
}
