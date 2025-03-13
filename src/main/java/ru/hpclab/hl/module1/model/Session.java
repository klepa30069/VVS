package ru.hpclab.hl.module1.model;


import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

// Посетитель
public class Session {

    @NonNull
    private UUID ID;
    @NonNull
    private Equipment equipment;
    @NonNull
    private Visitor visitor;
    @NonNull
    private LocalDateTime data;
    private int duration;

    public Session(@NonNull UUID ID, @NonNull Equipment equipment, @NonNull Visitor visitor, @NonNull LocalDateTime data, int duration) {
        this.ID = ID;
        this.equipment = equipment;
        this.visitor = visitor;
        this.data = data;
        this.duration = duration;
    }

    public Session(@NonNull UUID ID, @NonNull Equipment equipment, @NonNull Visitor visitor, @NonNull LocalDateTime data) {
        this.ID = ID;
        this.equipment = equipment;
        this.visitor = visitor;
        this.data = data;
    }

    public Session() {
    }

    @NonNull
    public UUID getId() {
        return ID;
    }

    public void setId(@NonNull UUID ID) {
        this.ID = ID;
    }

    @NonNull
    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(@NonNull Equipment equipment) {
        this.equipment = equipment;
    }

    @NonNull
    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(@NonNull Visitor visitor) {
        this.visitor = visitor;
    }

    @NonNull
    public LocalDateTime getData() {
        return data;
    }

    public void setData(@NonNull LocalDateTime data) {
        this.data = data;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Session {\n" +
                "  identifier=" + ID +
                ",\n  equipment=[\n" + equipment +
                "],\n  visitor=[\n" + visitor +
                "],\n  data=" + data +
                ((duration == 0) ? "" : ",\n  duration=" + duration) +
                "};";
    }
}
