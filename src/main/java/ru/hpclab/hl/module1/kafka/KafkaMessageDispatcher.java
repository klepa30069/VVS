package ru.hpclab.hl.module1.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.model.*;
import ru.hpclab.hl.module1.service.*;

@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcher {
    private final VisitorService visitorService;
    private final EquipmentService equipmentService;
    private final SessionService sessionService;
    private final ObjectMapper objectMapper;

    public void dispatch(KafkaMessage msg) {
        switch (msg.getEntity()) {
            case VISITOR -> handleVisitor(msg.getOperation(), msg.getPayload());
            case EQUIPMENT -> handleEquipment(msg.getOperation(), msg.getPayload());
            case SESSION -> handleSession(msg.getOperation(), msg.getPayload());
        }
    }

    private void handleVisitor(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> visitorService.addVisitor(deserialize(payload, Visitor.class));
            case DELETE -> visitorService.deleteVisitor(payload.get("id").asText());
            case CLEAR -> visitorService.clearAllVisitors();
            case GET -> visitorService.getVisitor(payload.get("id").asText());
            case GET_ALL -> visitorService.getAllVisitors();
        }
    }

    private void handleEquipment(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> equipmentService.addEquipment(deserialize(payload, Equipment.class));
            case DELETE -> equipmentService.deleteEquipment(payload.get("id").asText());
            case CLEAR -> equipmentService.clearAllEquipments();
            case GET -> equipmentService.getEquipment(payload.get("id").asText());
            case GET_ALL -> equipmentService.getAllEquipments();
        }
    }

    private void handleSession(OperationType op, JsonNode payload) {
        switch (op) {
            case POST -> sessionService.addSession(deserialize(payload, Session.class));
            case DELETE -> sessionService.deleteSession(payload.get("id").asText());
            case CLEAR -> sessionService.clearAllSessions();
            case GET -> sessionService.getSession(payload.get("id").asText());
            case GET_ALL -> sessionService.getAllSessions();
        }
    }

    private <T> T deserialize(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize payload to " + clazz.getSimpleName(), e);
        }
    }
}
