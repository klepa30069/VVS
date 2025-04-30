package ru.hpclab.hl.module1.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final ObjectMapper objectMapper;
    private final KafkaMessageDispatcher kafkaMessageDispatcher;
    private static final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(
            topics = "${kafka.topic:var12}",
            groupId = "${kafka.groupId:klepa300-consumer-group}",
            concurrency = "${kafka.concurrency:2}",
            properties = {
                "${max.poll.records:1000}"
            }
    )
    public void handleMessage(List<String> messageJsonList) {
        for (String messageJson : messageJsonList) {
            try {
                KafkaMessage message = objectMapper.readValue(messageJson, KafkaMessage.class);
                kafkaMessageDispatcher.dispatch(message);
            } catch (Exception e) {
                log.error("Error while parsing or dispatching Kafka message: {}", messageJson, e);
            }
        }
    }
}
