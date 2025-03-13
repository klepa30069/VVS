package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.EquipmentException;
import ru.hpclab.hl.module1.model.Equipment;

import java.util.*;

import static java.lang.String.format;

@Repository
public class EquipmentRepository {

    public static final String EQUIPMENT_NOT_FOUND_MSG = "Equipment with ID %s not found";
    public static final String EQUIPMENT_EXISTS_MSG = "Equipment with ID %s is already exists";

    private final Map<UUID, Equipment> equipments = new HashMap<>();

    public List<Equipment> findAll() {
        return new ArrayList<>(equipments.values());
    }

    public Equipment findById(UUID id) {
        final var equipment = equipments.get(id);
        if (equipment == null) {
            throw new EquipmentException(format(EQUIPMENT_NOT_FOUND_MSG, id));
        }
        return equipment;
    }

    public void delete(UUID id) {
        final var removed = equipments.remove(id);
        if (removed == null) {
            throw new EquipmentException(format(EQUIPMENT_NOT_FOUND_MSG, id));
        }
    }

    public Equipment save(Equipment equipment) {
        if (ObjectUtils.isEmpty(equipment.getId())) {
            equipment.setId(UUID.randomUUID());
        }

        final var equipmentData = equipments.get(equipment.getId());
        if (equipmentData != null) {
            throw new EquipmentException(format(EQUIPMENT_EXISTS_MSG, equipment.getId()));
        }

        equipments.put(equipment.getId(), equipment);

        return equipment;
    }

    public Equipment put(Equipment equipment) {
        final var equipmentData = equipments.get(equipment.getId());
        if (equipmentData == null) {
            throw new EquipmentException(format(EQUIPMENT_NOT_FOUND_MSG, equipment.getId()));
        }

        final var removed = equipments.remove(equipment.getId());
        if (removed != null) {
            equipments.put(equipment.getId(), equipment);
        } else {
            throw new EquipmentException(format(EQUIPMENT_NOT_FOUND_MSG, equipment.getId()));
        }

        return equipment;
    }

    public void clear(){
        equipments.clear();
    }

}
