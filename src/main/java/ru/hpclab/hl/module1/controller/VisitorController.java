package ru.hpclab.hl.module1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.service.VisitorService;
import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {
    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor) {
        return ResponseEntity.ok(visitorService.addVisitor(visitor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable String id) {
        Visitor visitor = visitorService.getVisitor(id);
        return visitor != null ? ResponseEntity.ok(visitor) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable String id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.noContent().build();
    }

    // Новый эндпоинт для очистки всех данных
    @Operation(summary = "Clear all visitors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "All visitors deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/clear")
    public ResponseEntity<Void> clearAllVisitors() {
        visitorService.clearAllVisitors();
        return ResponseEntity.noContent().build();
    }
}