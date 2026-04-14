package com.Home_Energy_Tracker.Energy_Usage_Service.service;

import com.Home_Energy_Tracker.Kafka.event.EnergyUsageEvent;

public interface EnergyUsageService {
    public void energyUsageEvent(EnergyUsageEvent energyUsageEvent);
    public void aggregateDeviceEnergyUsage();
   // public UsageDto getXDaysUsageForUser(Long userId, int days);
}
