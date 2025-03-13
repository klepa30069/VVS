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
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.repository.VisitorRepository;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class VisitorControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VisitorRepository visitorRepository;

    @BeforeEach
    public void init() {
        visitorRepository.clear();
    }

    @Test
    public void get_should_returnVisitor_when_visitorExists() throws Exception {
        Visitor visitor = visitorRepository.save(new Visitor(UUID.randomUUID(), "FIO"));
        String expectedJson = objectMapper.writeValueAsString(visitor);

        mvc.perform(get("/visitors/" + visitor.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
