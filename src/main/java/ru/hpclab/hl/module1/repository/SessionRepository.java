package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.SessionException;
import ru.hpclab.hl.module1.model.Session;

import java.util.*;

import static java.lang.String.format;

@Repository
public class SessionRepository {

    public static final String SESSION_NOT_FOUND_MSG = "Session with ID %s not found";
    public static final String SESSION_EXISTS_MSG = "Session with ID %s is already exists";

    private final Map<UUID, Session> sessions = new HashMap<>();

    public List<Session> findAll() {
        return new ArrayList<>(sessions.values());
    }

    public Session findById(UUID id) {
        final var session = sessions.get(id);
        if (session == null) {
            throw new SessionException(format(SESSION_NOT_FOUND_MSG, id));
        }
        return session;
    }

    public void delete(UUID id) {
        final var removed = sessions.remove(id);
        if (removed == null) {
            throw new SessionException(format(SESSION_NOT_FOUND_MSG, id));
        }
    }

    public Session save(Session session) {
        if (ObjectUtils.isEmpty(session.getId())) {
            session.setId(UUID.randomUUID());
        }

        final var sessionData = sessions.get(session.getId());
        if (sessionData != null) {
            throw new SessionException(format(SESSION_EXISTS_MSG, session.getId()));
        }

        sessions.put(session.getId(), session);

        return session;
    }

    public Session put(Session session) {
        final var sessionData = sessions.get(session.getId());
        if (sessionData == null) {
            throw new SessionException(format(SESSION_NOT_FOUND_MSG, session.getId()));
        }

        final var removed = sessions.remove(session.getId());
        if (removed != null) {
            sessions.put(session.getId(), session);
        } else {
            throw new SessionException(format(SESSION_NOT_FOUND_MSG, session.getId()));
        }

        return session;
    }

    public void clear(){
        sessions.clear();
    }

}
