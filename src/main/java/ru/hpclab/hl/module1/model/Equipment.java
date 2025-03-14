package ru.hpclab.hl.module1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Equipment type;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean status = false;

    public enum EquipmentType {
        TREADMILL, BIKE, CROSS_FIT, DUMBBELLS, HORIZONTAL_DEADLIFT
    }
}