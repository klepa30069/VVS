package ru.hpclab.hl.module1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID ID;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false) // Используем @JoinColumn вместо @Column
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false) // Используем @JoinColumn вместо @Column
    private Visitor visitor;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(columnDefinition = "int default 0")
    private int duration = 0;
}