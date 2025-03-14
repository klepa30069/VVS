package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SessionService {
    private final SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public Session addSession(Session session) {
        return repository.save(session);
    }

    public Session getSession(String id) {
        return repository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<Session> getAllSessions() {
        return repository.findAll();
    }

    public void deleteSession(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    public double calculateAverageDurationForMonth(UUID visitorId) {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        LocalDateTime now = LocalDateTime.now();

        // Получаем среднюю продолжительность за последний месяц
        Double averageDuration = repository.averageDurationByVisitorIdAndDateBetween(visitorId, oneMonthAgo, now);

        return averageDuration != null ? averageDuration : 0.0; // Если нет сессий, возвращаем 0
    }
}
