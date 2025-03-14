package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.repository.EquipmentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class EquipmentService {
    private final EquipmentRepository repository;

    public EquipmentService(EquipmentRepository repository) {
        this.repository = repository;
    }

    public Equipment addEquipment(Equipment equipment) {
        return repository.save(equipment);
    }

    public Equipment getEquipment(String id) {
        return repository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<Equipment> getAllEquipments() {
        return repository.findAll();
    }

    public void deleteEquipment(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
