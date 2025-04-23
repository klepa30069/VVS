package ru.hpclab.hl.module1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.service.ObservabilityService;
import ru.hpclab.hl.module1.service.SessionService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    private final ObservabilityService observability;

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(sessionService.addSession(session));
        } finally {
            observability.recordTiming(
                "controller.sessions.create",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSession(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        try {
            Session session = sessionService.getSession(id);
            return session != null ? ResponseEntity.ok(session) : ResponseEntity.notFound().build();
        } finally {
            observability.recordTiming(
                "controller.sessions.get_by_id",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(sessionService.getAllSessions());
        } finally {
            observability.recordTiming(
                "controller.sessions.get_all",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        try {
            sessionService.deleteSession(id);
            return ResponseEntity.noContent().build();
        } finally {
            observability.recordTiming(
                "controller.sessions.delete",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping("/average-duration-with-year")
    public ResponseEntity<Double> getAverageDurationWithYear(
            @RequestParam String fio,
            @RequestParam int month,
            @RequestParam int year) {
        long startTime = System.currentTimeMillis();
        try {
            double average = sessionService.getAverageDurationByFioMonthAndYear(fio, month, year);
            return ResponseEntity.ok(average);
        } finally {
            observability.recordTiming(
                "controller.sessions.average_duration",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<List<Session>> getSessionsByVisitor(@PathVariable UUID visitorId) {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(sessionService.getSessionsByVisitor(visitorId));
        } finally {
            observability.recordTiming(
                "controller.sessions.by_visitor",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<Session>> getSessionsByEquipment(@PathVariable UUID equipmentId) {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(sessionService.getSessionsByEquipment(equipmentId));
        } finally {
            observability.recordTiming(
                "controller.sessions.by_equipment",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @Operation(summary = "Clear all sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "All sessions deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/clear")
    public ResponseEntity<Void> clearAllSessions() {
        long startTime = System.currentTimeMillis();
        try {
            sessionService.clearAllSessions();
            return ResponseEntity.noContent().build();
        } finally {
            observability.recordTiming(
                "controller.sessions.clear_all",
                System.currentTimeMillis() - startTime
            );
        }
    }
}
