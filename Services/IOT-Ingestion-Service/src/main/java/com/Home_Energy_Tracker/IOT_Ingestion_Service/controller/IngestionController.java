package com.Home_Energy_Tracker.IOT_Ingestion_Service.controller;

import com.Home_Energy_Tracker.IOT_Ingestion_Service.dto.EnergyUsageDto;
import com.Home_Energy_Tracker.IOT_Ingestion_Service.service.IngestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingestion")
@Tag(name = "IOT Energy Usage Service API- RestController", description = "Energy usage data ingestion endpoints")
public class IngestionController {

    private final IngestionService ingestionService;


//    @ApiResponse(responseCode = "202", description = "Energy usage data accepted for processing",
//        content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))

    @Operation(summary = "Ingest energy usage data", description = "Receives energy consumption data from IoT devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Energy usage data accepted for processing"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/energy-usage")
    public ResponseEntity<String> ingestionData(@RequestBody EnergyUsageDto energyUsageDto) {
        ingestionService.ingestEnergyUsage(energyUsageDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Energy usage data received and queued for processing");
    }

}
