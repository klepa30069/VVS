package ru.hpclab.hl.module1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final ObjectMapper objectMapper;
    private final KafkaMessageDispatcher dispatcher;

    @KafkaListener(topics = "${kafka.topic.name:var12}")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            KafkaMessage message = objectMapper.readValue(record.value(), KafkaMessage.class);
            dispatcher.dispatch(message);
        } catch (Exception e) {
            log.error("Failed to process Kafka message: {}", record.value(), e);
        }
    }
}
