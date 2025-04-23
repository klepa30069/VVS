package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final ObservabilityService observability;

    public Session addSession(Session session) {
        long startTime = System.currentTimeMillis();
        try {
            return sessionRepository.save(session);
        } finally {
            observability.recordTiming(
                "service.sessions.create",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public Session getSession(String id) {
        long startTime = System.currentTimeMillis();
        try {
            return sessionRepository.findById(UUID.fromString(id)).orElse(null);
        } finally {
            observability.recordTiming(
                "service.sessions.get_by_id",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public List<Session> getAllSessions() {
        long startTime = System.currentTimeMillis();
        try {
            return sessionRepository.findAll();
        } finally {
            observability.recordTiming(
                "service.sessions.get_all",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public void deleteSession(String id) {
        long startTime = System.currentTimeMillis();
        try {
            sessionRepository.deleteById(UUID.fromString(id));
        } finally {
            observability.recordTiming(
                "service.sessions.delete",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public double getAverageDurationByFioMonthAndYear(String fio, int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        long startTime = System.currentTimeMillis();
        try {
            Double average = sessionRepository.findAverageDurationWithTiming(
                fio, month, year, observability
            );
            return average != null ? average : 0.0;
        } finally {
            observability.recordTiming(
                "service.sessions.avg_duration",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public void clearAllSessions() {
        long startTime = System.currentTimeMillis();
        try {
            sessionRepository.deleteAll();
        } finally {
            observability.recordTiming(
                "service.sessions.clear_all",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public List<Session> getSessionsByVisitor(UUID visitorId) {
        long startTime = System.currentTimeMillis();
        try {
            return sessionRepository.findByVisitorIdWithTiming(visitorId, observability);
        } finally {
            observability.recordTiming(
                "service.sessions.by_visitor",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public List<Session> getSessionsByEquipment(UUID equipmentId) {
        long startTime = System.currentTimeMillis();
        try {
            return sessionRepository.findByEquipmentIdWithTiming(equipmentId, observability);
        } finally {
            observability.recordTiming(
                "service.sessions.by_equipment",
                System.currentTimeMillis() - startTime
            );
        }
    }
}
