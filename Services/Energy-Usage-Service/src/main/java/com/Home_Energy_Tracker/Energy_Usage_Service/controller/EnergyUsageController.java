package com.Home_Energy_Tracker.Energy_Usage_Service.controller;

import com.Home_Energy_Tracker.Energy_Usage_Service.dto.UsageDto;
import com.Home_Energy_Tracker.Energy_Usage_Service.service.EnergyUsageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/energy-usage")
@Tag(name = "Energy Usage API", description = "Read aggregated energy usage for users")
public class EnergyUsageController {

    private final EnergyUsageService energyUsageService;

    @Operation(summary = "Get user energy usage", description = "Returns aggregated device energy usage for the given user and number of days")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved usage data"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "User or usage data not found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UsageDto> getUserDeviceUsage(
            @Parameter(description = "User ID", example = "1") @PathVariable Long userId,
            @Parameter(description = "Number of days to include", example = "3")
            @RequestParam(defaultValue = "3") int days) {
        final UsageDto usage = energyUsageService.getXDaysUsageForUser(userId, days);
        return ResponseEntity.ok(usage);
    }

}
