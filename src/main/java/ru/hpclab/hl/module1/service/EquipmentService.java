package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.repository.EquipmentRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final ObservabilityService observability;

    public Equipment addEquipment(Equipment equipment) {
        long startTime = System.currentTimeMillis();
        try {
            return equipmentRepository.save(equipment);
        } finally {
            observability.recordTiming(
                "service.equipments.create",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public Equipment getEquipment(String id) {
        long startTime = System.currentTimeMillis();
        try {
            return equipmentRepository.findByIdWithTiming(
                UUID.fromString(id),
                observability
            );
        } finally {
            observability.recordTiming(
                "service.equipments.get_by_id",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public List<Equipment> getAllEquipments() {
        long startTime = System.currentTimeMillis();
        try {
            return equipmentRepository.findAll();
        } finally {
            observability.recordTiming(
                "service.equipments.get_all",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public void deleteEquipment(String id) {
        long startTime = System.currentTimeMillis();
        try {
            equipmentRepository.deleteById(UUID.fromString(id));
        } finally {
            observability.recordTiming(
                "service.equipments.delete",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public void clearAllEquipments() {
        long startTime = System.currentTimeMillis();
        try {
            equipmentRepository.deleteAll();
        } finally {
            observability.recordTiming(
                "service.equipments.clear_all",
                System.currentTimeMillis() - startTime
            );
        }
    }
}
