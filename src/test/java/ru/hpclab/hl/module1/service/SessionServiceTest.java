/*
package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SessionServiceTest.SessionServiceTestConfiguration.class})
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionRepository sessionRepository;

    private static final LocalDateTime data = LocalDateTime.now();
    private static Equipment equipment = new Equipment();
    private static Visitor visitor = new Visitor();


    @Test
    public void testCreateAndGet(){
        //create
        Session session = new Session(UUID.randomUUID(), equipment.getId(), visitor.getId(), data);

        Session savedSession = sessionService.saveSession(session);

        Assertions.assertEquals(session.getData(), savedSession.getData());
        Mockito.verify(sessionRepository, Mockito.times(1)).save(session);

        //getAll
        List<Session> sessionList = sessionService.getAllSessions();

        Assertions.assertEquals(data.plusDays(12), sessionList.get(0).getData());
        Assertions.assertEquals(data.plusDays(21), sessionList.get(1).getData());
        Mockito.verify(sessionRepository, Mockito.times(1)).findAll();

    }

    @Configuration
    static class SessionServiceTestConfiguration {
        @Bean
        SessionRepository sessionRepository() {
            SessionRepository sessionRepository = mock(SessionRepository.class);
            when(sessionRepository.save(any())).thenReturn(new Session(UUID.randomUUID(), equipment.getId(), visitor.getId(), data));
            when(sessionRepository.findAll())
                    .thenReturn(Arrays.asList(new Session(UUID.randomUUID(), equipment.getId(), visitor.getId(), data.plusDays(12)),
                            new Session(UUID.randomUUID(), equipment.getId(), visitor.getId(), data.plusDays(21))));
            return sessionRepository;
        }

        @Bean
        SessionService SessionService(SessionRepository sessionRepository){
            return new SessionService(sessionRepository);
        }
    }

}
*/
