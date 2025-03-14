package ru.hpclab.hl.module1.controller;

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
}