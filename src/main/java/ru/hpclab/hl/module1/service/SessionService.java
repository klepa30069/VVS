package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.util.List;
import java.util.UUID;

public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(String id) {
        return sessionRepository.findById(UUID.fromString(id));
    }

    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    public void deleteSession(String id) {
        sessionRepository.delete(UUID.fromString(id));
    }

    public Session updateSession(String id, Session session) {
        session.setId(UUID.fromString(id));
        return sessionRepository.put(session);
    }
}
