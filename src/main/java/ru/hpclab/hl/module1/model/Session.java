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
    @Column(nullable = false)
    private Equipment equipment;
    @ManyToOne
    @Column(nullable = false)
    private Visitor visitor;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(columnDefinition = "int default 0")
    private int duration = 0;
}
