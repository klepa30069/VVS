package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.service.EquipmentService;

import java.util.List;

@RestController
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/equipment")
    public List<Equipment> getEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/equipments/{id}")
    public Equipment getEquipmentById(@PathVariable String id) {
        return equipmentService.getEquipmentById(id);
    }

    @DeleteMapping("/equipments/{id}")
    public void deleteEquipment(@PathVariable String id) {
        equipmentService.deleteEquipment(id);
    }

    @PostMapping(value = "/equipments/")
    public Equipment saveEquipment(@RequestBody Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }

    @PutMapping(value = "/equipments/{id}")
    public Equipment updateEquipment(@PathVariable(required = false) String id, @RequestBody Equipment equipment) {
        return equipmentService.updateEquipment(id, equipment);
    }

}
