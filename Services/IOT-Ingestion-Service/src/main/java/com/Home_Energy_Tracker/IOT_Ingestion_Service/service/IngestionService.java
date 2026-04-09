package com.Home_Energy_Tracker.IOT_Ingestion_Service.service;

import com.Home_Energy_Tracker.IOT_Ingestion_Service.dto.EnergyUsageDto;

import java.util.List;

public interface IngestionService {
    public void ingestEnergyUsage(EnergyUsageDto input);

}
