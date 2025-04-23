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
