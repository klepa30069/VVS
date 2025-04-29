package ru.hpclab.hl.module1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.service.VisitorService;
import ru.hpclab.hl.module1.service.EquipmentService;
import ru.hpclab.hl.module1.service.SessionService;

@Configuration
public class KafkaDispatcherConfig {
    @Bean
    public KafkaMessageDispatcher kafkaMessageDispatcher(
            VisitorService visitorService,
            EquipmentService equipmentService,
            SessionService sessionService,
            ObjectMapper objectMapper
    ) {
        return new KafkaMessageDispatcher(
                visitorService,
                equipmentService,
                sessionService,
                objectMapper
        );
    }
}
