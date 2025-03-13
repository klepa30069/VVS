package ru.hpclab.hl.module1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class SessionControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

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
        Session session = sessionRepository.save(new Session(UUID.randomUUID(), new Equipment(UUID.randomUUID(), "bysical", true), new Visitor(UUID.randomUUID(), "name"), LocalDateTime.now()));
        String expectedJson = objectMapper.writeValueAsString(session);

        mvc.perform(get("/sessions/" + session.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
