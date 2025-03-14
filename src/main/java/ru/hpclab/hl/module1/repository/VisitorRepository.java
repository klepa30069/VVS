package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hpclab.hl.module1.model.Visitor;

import java.util.UUID;

public interface VisitorRepository extends JpaRepository<Visitor, UUID> {
}
