package ru.hpclab.hl.module1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.repository.VisitorRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final ObservabilityService observability;

    public Visitor addVisitor(Visitor visitor) {
        long startTime = System.currentTimeMillis();
        try {
            return visitorRepository.save(visitor);
        } finally {
            observability.recordTiming(
                "service.visitors.create",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public Visitor getVisitor(String id) {
        long startTime = System.currentTimeMillis();
        try {
            return visitorRepository.findByIdWithTiming(
                UUID.fromString(id),
                observability
            );
        } finally {
            observability.recordTiming(
                "service.visitors.get_by_id",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public List<Visitor> getAllVisitors() {
        long startTime = System.currentTimeMillis();
        try {
            return visitorRepository.findAll();
        } finally {
            observability.recordTiming(
                "service.visitors.get_all",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public void deleteVisitor(String id) {
        long startTime = System.currentTimeMillis();
        try {
            visitorRepository.deleteById(UUID.fromString(id));
        } finally {
            observability.recordTiming(
                "service.visitors.delete",
                System.currentTimeMillis() - startTime
            );
        }
    }

    public void clearAllVisitors() {
        long startTime = System.currentTimeMillis();
        try {
            visitorRepository.deleteAll();
        } finally {
            observability.recordTiming(
                "service.visitors.clear_all",
                System.currentTimeMillis() - startTime
            );
        }
    }
}
