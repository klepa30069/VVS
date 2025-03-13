package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RestController
public class AverageDurationController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/average-duration/{visitorId}")
    public ResponseEntity<Double> getAverageDuration(@PathVariable UUID visitorId) {
        List<Session> sessions = sessionRepository.findByVisitorId(visitorId);

        if (sessions.isEmpty()) {
            return ResponseEntity.ok(0.0); // Если нет занятий, возвращаем 0
        }

        double totalDuration = sessions.stream()
                .mapToLong(Session::getDuration) // Используем продолжительность сессии
                .sum();

        double averageDuration = totalDuration / sessions.size();
        return ResponseEntity.ok(averageDuration);
    }
}