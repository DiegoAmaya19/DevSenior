package com.devsenior.diego.bibliokeep.controller;

import com.devsenior.diego.bibliokeep.model.dto.response.DashboardResponseDTO;
import com.devsenior.diego.bibliokeep.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/dashboard")
    public DashboardResponseDTO getDashboard(String userId) {
        var ownerId = UUID.fromString(userId);
        return statsService.getDashboard(ownerId);
    }
}
