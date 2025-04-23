package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.service.ObservabilityService;

import java.util.UUID;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, UUID> {

    default Visitor findByIdWithTiming(UUID id, ObservabilityService observability) {
        long startTime = System.currentTimeMillis();
        try {
            return findById(id).orElse(null);
        } finally {
            observability.recordTiming(
                "db.visitors.find_by_id",
                System.currentTimeMillis() - startTime
            );
        }
    }
}
