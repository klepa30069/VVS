package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.repository.VisitorRepository;

import java.util.List;
import java.util.UUID;

public class VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor getVisitorById(String id) {
        return visitorRepository.findById(UUID.fromString(id));
    }

    public Visitor saveVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public void deleteVisitor(String id) {
        visitorRepository.delete(UUID.fromString(id));
    }

    public Visitor updateVisitor(String id, Visitor visitor) {
        visitor.setId(UUID.fromString(id));
        return visitorRepository.put(visitor);
    }
}
