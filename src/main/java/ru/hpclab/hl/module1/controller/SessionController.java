package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.service.SessionService;

import java.util.List;

@RestController
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/session")
    public List<Session> getSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/sessions/{id}")
    public Session getSessionById(@PathVariable String id) {
        return sessionService.getSessionById(id);
    }

    @DeleteMapping("/sessions/{id}")
    public void deleteSession(@PathVariable String id) {
        sessionService.deleteSession(id);
    }

    @PostMapping(value = "/sessions/")
    public Session saveSession(@RequestBody Session session) {
        return sessionService.saveSession(session);
    }

    @PutMapping(value = "/sessions/{id}")
    public Session updateSession(@PathVariable(required = false) String id, @RequestBody Session session) {
        return sessionService.updateSession(id, session);
    }

}
