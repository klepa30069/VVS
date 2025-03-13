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
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.repository.VisitorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VisitorServiceTest.VisitorServiceTestConfiguration.class})
public class VisitorServiceTest {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private VisitorRepository visitorRepository;

    @Test
    public void testCreateAndGet(){
        //create
        Visitor visitor = new Visitor(UUID.randomUUID(), "FIO");

        Visitor savedVisitor = visitorService.saveVisitor(visitor);

        Assertions.assertEquals(visitor.getFio(), savedVisitor.getFio());
        Mockito.verify(visitorRepository, Mockito.times(1)).save(visitor);

        //getAll
        List<Visitor> visitorList = visitorService.getAllVisitors();

        Assertions.assertEquals("FIO", visitorList.get(0).getFio());
        Assertions.assertEquals("FIO2", visitorList.get(1).getFio());
        Assertions.assertEquals("132", visitorList.get(1).getSubscription());
        Assertions.assertEquals(120.5, visitorList.get(1).getHeight());
        Assertions.assertEquals(30.5, visitorList.get(1).getWeight());
        Mockito.verify(visitorRepository, Mockito.times(1)).findAll();

    }

    @Configuration
    static class VisitorServiceTestConfiguration {

        @Bean
        VisitorRepository visitorRepository() {
            VisitorRepository visitorRepository = mock(VisitorRepository.class);
            when(visitorRepository.save(any())).thenReturn(new Visitor(UUID.randomUUID(), "FIO"));
            when(visitorRepository.findAll())
                    .thenReturn(Arrays.asList(new Visitor(UUID.randomUUID(), "FIO"),
                            new Visitor(UUID.randomUUID(), "FIO2", "132", 30.5, 120.5)));
            return visitorRepository;
        }

        @Bean
        VisitorService VisitorService(VisitorRepository visitorRepository){
            return new VisitorService(visitorRepository);
        }
    }

}
