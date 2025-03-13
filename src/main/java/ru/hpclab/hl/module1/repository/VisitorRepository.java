package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.VisitorException;
import ru.hpclab.hl.module1.model.Visitor;

import java.util.*;

import static java.lang.String.format;

@Repository
public class VisitorRepository {

    public static final String VISITOR_NOT_FOUND_MSG = "Visitor with ID %s not found";
    public static final String VISITOR_EXISTS_MSG = "Visitor with ID %s is already exists";

    private final Map<UUID, Visitor> visitors = new HashMap<>();

    public List<Visitor> findAll() {
        return new ArrayList<>(visitors.values());
    }

    public Visitor findById(UUID id) {
        final var visitor = visitors.get(id);
        if (visitor == null) {
            throw new VisitorException(format(VISITOR_NOT_FOUND_MSG, id));
        }
        return visitor;
    }

    public void delete(UUID id) {
        final var removed = visitors.remove(id);
        if (removed == null) {
            throw new VisitorException(format(VISITOR_NOT_FOUND_MSG, id));
        }
    }

    public Visitor save(Visitor visitor) {
        if (ObjectUtils.isEmpty(visitor.getId())) {
            visitor.setId(UUID.randomUUID());
        }

        final var visitorData = visitors.get(visitor.getId());
        if (visitorData != null) {
            throw new VisitorException(format(VISITOR_EXISTS_MSG, visitor.getId()));
        }

        visitors.put(visitor.getId(), visitor);

        return visitor;
    }

    public Visitor put(Visitor visitor) {
        final var visitorData = visitors.get(visitor.getId());
        if (visitorData == null) {
            throw new VisitorException(format(VISITOR_NOT_FOUND_MSG, visitor.getId()));
        }

        final var removed = visitors.remove(visitor.getId());
        if (removed != null) {
            visitors.put(visitor.getId(), visitor);
        } else {
            throw new VisitorException(format(VISITOR_NOT_FOUND_MSG, visitor.getId()));
        }

        return visitor;
    }

    public void clear(){
        visitors.clear();
    }

}
