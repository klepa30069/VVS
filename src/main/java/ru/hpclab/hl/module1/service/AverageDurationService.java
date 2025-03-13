package ru.hpclab.hl.module1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AverageDurationService {

    @Autowired
    private SessionRepository sessionRepository;

    public double calculateAverageDuration(UUID visitorId) {
        List<Session> sessions = sessionRepository.findByVisitorId(visitorId);

        if (sessions.isEmpty()) {
            return 0.0; // If there are no sessions, return 0
        }

        double totalDuration = sessions.stream()
                .mapToLong(Session::getDuration) // Use session duration
                .sum();

        return totalDuration / sessions.size();
    }
}