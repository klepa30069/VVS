package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Visitor;
import ru.hpclab.hl.module1.repository.VisitorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class VisitorService {
    private final VisitorRepository repository;

    public VisitorService(VisitorRepository repository) {
        this.repository = repository;
    }

    public Visitor addVisitor(Visitor visitor) {
        return repository.save(visitor);
    }

    public Visitor getVisitor(String id) {
        return repository.findById(UUID.fromString(id)).orElse(null);
    }

    public List<Visitor> getAllVisitors() {
        return repository.findAll();
    }

    public void deleteVisitor(String id) {
        repository.deleteById(UUID.fromString(id));
    }
}
