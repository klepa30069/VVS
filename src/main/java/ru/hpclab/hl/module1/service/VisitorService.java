package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.repository.VisitorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public Visitor getVisitor(String id) {
        return visitorRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public void deleteVisitor(String id) {
        visitorRepository.deleteById(UUID.fromString(id));
    }

    public void clearAllVisitors() {
        visitorRepository.deleteAll();
    }
}
