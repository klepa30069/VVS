/*
package ru.hpclab.hl.module1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.hpclab.hl.module1.Application;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class SessionControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    public void init() {
        sessionRepository.clear();
    }

    @Test
    public void get_should_returnSession_when_sessionExists() throws Exception {
        LocalDateTime data = LocalDateTime.now();

        UUID id = UUID.fromString("265ffb79-b6c3-40ec-854e-3fd6522a61af");
        Equipment equipment = new Equipment(UUID.randomUUID(), "bicycle", true);
        Visitor visitor = new Visitor(UUID.randomUUID(), "name");
        Session session = new Session(id, equipment.getId(), visitor.getId(), data);
        sessionRepository.save(session); // Сохраняем сессию в репозитории

        // Serialize the session to JSON

        mvc.perform(get("/sessions/" + session.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"equipmentID\":\"" + equipment.getId() + "\",\"visitorID\":\"" + visitor.getId() + "\",\"data\":\"" + session.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")) + "\",\"duration\":0,\"id\":\"265ffb79-b6c3-40ec-854e-3fd6522a61af\"}"));
    }
}
*/
