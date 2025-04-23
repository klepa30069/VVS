package ru.hpclab.hl.module1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.service.ObservabilityService;
import ru.hpclab.hl.module1.service.VisitorService;
import java.util.List;

@RestController
@RequestMapping("/visitors")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;
    private final ObservabilityService observability;

    @PostMapping
    public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor) {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(visitorService.addVisitor(visitor));
        } finally {
            observability.recordTiming(
                "controller.visitors.create",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        try {
            Visitor visitor = visitorService.getVisitor(id);
            return visitor != null ? ResponseEntity.ok(visitor) : ResponseEntity.notFound().build();
        } finally {
            observability.recordTiming(
                "controller.visitors.get_by_id",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(visitorService.getAllVisitors());
        } finally {
            observability.recordTiming(
                "controller.visitors.get_all", 
                System.currentTimeMillis() - startTime
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        try {
            visitorService.deleteVisitor(id);
            return ResponseEntity.noContent().build();
        } finally {
            observability.recordTiming(
                "controller.visitors.delete", 
                System.currentTimeMillis() - startTime
            );
        }
    }

    @Operation(summary = "Clear all visitors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "All visitors deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/clear")
    public ResponseEntity<Void> clearAllVisitors() {
        long startTime = System.currentTimeMillis();
        try {
            visitorService.clearAllVisitors();
            return ResponseEntity.noContent().build();
        } finally {
            observability.recordTiming(
                "controller.visitors.clear_all", 
                System.currentTimeMillis() - startTime
            );
        }
    }
}
