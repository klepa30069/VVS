package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.service.ObservabilityService;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Query("SELECT AVG(s.duration) FROM Session s WHERE s.visitorId IN (SELECT v.id FROM Visitor v WHERE v.fio = :fio) AND EXTRACT(MONTH FROM s.date) = :month AND EXTRACT(YEAR FROM s.date) = :year")
    Double findAverageDurationByFioMonthAndYear(
        @Param("fio") String fio,
        @Param("month") int month,
        @Param("year") int year);

    List<Session> findByVisitorId(UUID visitorId);

    List<Session> findByEquipmentId(UUID equipmentId);

    default Double findAverageDurationWithTiming(String fio, int month, int year, ObservabilityService observability) {
        long startTime = System.currentTimeMillis();
        try {
            return findAverageDurationByFioMonthAndYear(fio, month, year);
        } finally {
            observability.recordTiming(
                "db.sessions.avg_duration",
                System.currentTimeMillis() - startTime
            );
        }
    }

    default List<Session> findByVisitorIdWithTiming(UUID visitorId, ObservabilityService observability) {
        long startTime = System.currentTimeMillis();
        try {
            return findByVisitorId(visitorId);
        } finally {
            observability.recordTiming(
                "db.sessions.by_visitor",
                System.currentTimeMillis() - startTime
            );
        }
    }

    default List<Session> findByEquipmentIdWithTiming(UUID equipmentId, ObservabilityService observability) {
        long startTime = System.currentTimeMillis();
        try {
            return findByEquipmentId(equipmentId);
        } finally {
            observability.recordTiming(
                "db.sessions.by_equipment",
                System.currentTimeMillis() - startTime
            );
        }
    }
}
