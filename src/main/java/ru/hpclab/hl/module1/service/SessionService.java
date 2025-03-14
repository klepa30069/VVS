package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session getSession(String id) {
        return sessionRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public void deleteSession(String id) {
        sessionRepository.deleteById(UUID.fromString(id));
    }

    public double calculateAverageDurationForMonth(UUID visitorId) {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        LocalDateTime now = LocalDateTime.now();

        // Получаем среднюю продолжительность за последний месяц
        Double averageDuration = sessionRepository.averageDurationByVisitorIdAndDateBetween(visitorId, oneMonthAgo, now);

        return averageDuration != null ? averageDuration : 0.0; // Если нет сессий, возвращаем 0
    }
}
