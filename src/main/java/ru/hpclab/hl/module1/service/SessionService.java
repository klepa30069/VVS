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
}
