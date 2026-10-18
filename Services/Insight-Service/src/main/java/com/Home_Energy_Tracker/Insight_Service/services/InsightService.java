package com.Home_Energy_Tracker.Insight_Service.services;

import com.Home_Energy_Tracker.Insight_Service.client.UsageClient;
import com.Home_Energy_Tracker.Insight_Service.dto.DeviceDto;
import com.Home_Energy_Tracker.Insight_Service.dto.InsightDto;
import com.Home_Energy_Tracker.Insight_Service.dto.UsageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class InsightService {
    private final UsageClient usageClient;
    private final OllamaChatModel ollamaChatModel;

    public InsightService(UsageClient usageClient,
                          OllamaChatModel ollamaChatModel) {
        this.usageClient = usageClient;
        this.ollamaChatModel = ollamaChatModel;
    }

    public InsightDto getSavingsTips (Long userId) {
        // Fetch data from Usage Service
        final UsageDto usageData = usageClient.getXDaysUsageForUser(userId, 3);

        double totalUsage = usageData.devices().stream()
                .mapToDouble(DeviceDto::energyConsumed)
                .sum();

        log.info ("[Saving Tips] Calling Ollama for userId {} with total usage {}",
                userId, totalUsage);

        String prompt = "This is my total consumption over the past 3 days." +
                "How can I reduce my energy consumption? How does it compare to average households?" +
                "Total energy used: \n" +
                totalUsage;

        ChatResponse response = ollamaChatModel.call(
                Prompt.builder()
                        .content(prompt)
                        .build());

        return InsightDto.builder()
                .userId(userId)
                .tips(Objects.requireNonNull(response.getResult()).getOutput().getText())
                .energyUsage(totalUsage)
                .build();
    }

    public InsightDto getOverview (Long userId) {
        // Fetch data from Usage Service
        final UsageDto usageData = usageClient.getXDaysUsageForUser(userId, 3);

        double totalUsage = usageData.devices().stream()
                .mapToDouble(DeviceDto::energyConsumed)
                .sum();

        log.info ("Calling Ollama for userId {} with total usage {}",
                userId, totalUsage);

        String prompt = new StringBuilder()
                .append("Analyse the following energy usage data and provide a " +
                        "concise overview with actionable insights.")
                .append("This data is the aggregate data for the past 3 days.")
                .append("Usage Data: \n")
                .append(usageData.devices())
                .toString();

        ChatResponse response = ollamaChatModel.call(
                Prompt.builder()
                        .content(prompt)
                        .build());

        return InsightDto.builder()
                .userId(userId)
                .tips(Objects.requireNonNull(response.getResult()).getOutput().getText())
                .energyUsage(totalUsage)
                .build();
    }
}
