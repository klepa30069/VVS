package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.service.AverageDurationService;

import java.util.UUID;

@RestController
public class AverageDurationController {

    @Autowired
    private AverageDurationService averageDurationService;

    @GetMapping("/average-duration/{visitorId}")
    public ResponseEntity<Double> getAverageDuration(@PathVariable UUID visitorId) {
        double averageDuration = averageDurationService.calculateAverageDuration(visitorId);
        return ResponseEntity.ok(averageDuration);
    }
}