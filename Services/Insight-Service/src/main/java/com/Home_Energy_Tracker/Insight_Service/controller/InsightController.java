package com.Home_Energy_Tracker.Insight_Service.controller;

import com.Home_Energy_Tracker.Insight_Service.dto.InsightDto;
import com.Home_Energy_Tracker.Insight_Service.services.InsightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/insight")
public class InsightController {
    private final InsightService insightService;

    public InsightController(InsightService insightService) {
        this.insightService = insightService;
    }

    @Operation(summary = "Saving Tips", description = "Energy Saving Tips")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated Saving Tips "),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/saving-tips/{userId}")
    public ResponseEntity<InsightDto> getSavingTips(@PathVariable Long userId) {
        final InsightDto insight = insightService.getSavingsTips(userId);
        return ResponseEntity.ok(insight);
    }

    @Operation(summary = "Overview -Saving Tips", description = "Energy Saving Tips for the requested days")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated Saving Tips for the requested days "),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/overview/{userId}")
    public ResponseEntity<InsightDto> getOverview(@PathVariable Long userId) {
        final InsightDto insight = insightService.getOverview(userId);
        return ResponseEntity.ok(insight);
    }
}
