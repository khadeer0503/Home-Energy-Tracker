package com.Home_Energy_Tracker.Energy_Usage_Service.controller;

import com.Home_Energy_Tracker.Energy_Usage_Service.service.EnergyUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/energy-usage")
public class EnergyUsageController {

    private final EnergyUsageService energyUsageService;

}
