package ru.hpclab.hl.module1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.service.EquipmentService;
import ru.hpclab.hl.module1.service.ObservabilityService;
import java.util.List;

@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final ObservabilityService observability;

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(equipmentService.addEquipment(equipment));
        } finally {
            observability.recordTiming(
                "controller.equipments.create",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        try {
            Equipment equipment = equipmentService.getEquipment(id);
            return equipment != null ? ResponseEntity.ok(equipment) : ResponseEntity.notFound().build();
        } finally {
            observability.recordTiming(
                "controller.equipments.get_by_id",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        long startTime = System.currentTimeMillis();
        try {
            return ResponseEntity.ok(equipmentService.getAllEquipments());
        } finally {
            observability.recordTiming(
                "controller.equipments.get_all",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.noContent().build();
        } finally {
            observability.recordTiming(
                "controller.equipments.delete",
                System.currentTimeMillis() - startTime
            );
        }
    }

    @Operation(summary = "Clear all equipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "All equipment deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/clear")
    public ResponseEntity<Void> clearAllEquipments() {
        long startTime = System.currentTimeMillis();
        try {
            equipmentService.clearAllEquipments();
            return ResponseEntity.noContent().build();
        } finally {
            observability.recordTiming(
                "controller.equipments.clear_all",
                System.currentTimeMillis() - startTime
            );
        }
    }
}
