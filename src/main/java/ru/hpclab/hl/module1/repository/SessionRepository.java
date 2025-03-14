package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hpclab.hl.module1.model.Session;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    @Query("SELECT AVG(s.duration) FROM Session s WHERE s.visitor.id = :visitorId AND s.date BETWEEN :startDate AND :endDate")
    Double averageDurationByVisitorIdAndDateBetween(@Param("visitorId") UUID visitorId,
                                                    @Param("startDate") LocalDateTime startDate,
                                                    @Param("endDate") LocalDateTime endDate);
}
