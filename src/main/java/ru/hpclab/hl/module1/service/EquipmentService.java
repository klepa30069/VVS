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

    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment getEquipment(String id) {
        return equipmentRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public void deleteEquipment(String id) {
        equipmentRepository.deleteById(UUID.fromString(id));
    }
}
