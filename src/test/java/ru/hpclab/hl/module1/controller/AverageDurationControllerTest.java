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
import ru.hpclab.hl.module1.model.Equipment;
import ru.hpclab.hl.module1.model.Session;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class AverageDurationControllerTest {
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
    public void get_should_returnAverageDuration_when_sessionsExist() throws Exception {
        LocalDateTime data = LocalDateTime.now();
        Equipment equipment = new Equipment(UUID.randomUUID(), "bicycle", true);
        Visitor visitor = new Visitor(UUID.randomUUID(), "name");

        // Создаем сессии с разной продолжительностью
        for (int i = 0; i < 5; i++) {
            Session session = new Session(UUID.randomUUID(), equipment.getId(), visitor.getId(), data.minusMinutes(i * 10), i * 10 + 5);
            sessionRepository.save(session); // Сохраняем сессию в репозитории
        }

        // Ожидаем, что средняя продолжительность будет равна (5 + 15 + 25 + 35 + 45) / 5 = 25
        double expectedAverageDuration = 25.0;

        mvc.perform(get("/average-duration/" + visitor.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedAverageDuration)));
    }
}
