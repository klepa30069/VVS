package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.repository.EquipmentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(String id) {
        return equipmentRepository.findById(UUID.fromString(id));
    }

    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(String id) {
        equipmentRepository.delete(UUID.fromString(id));
    }

    public Equipment updateEquipment(String id, Equipment equipment) {
        equipment.setId(UUID.fromString(id));
        return equipmentRepository.put(equipment);
    }
}
