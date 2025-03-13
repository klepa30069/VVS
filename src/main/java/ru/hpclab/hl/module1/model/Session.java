package ru.hpclab.hl.module1.model;


import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

// Посетитель
public class Session {

    @NonNull
    private UUID ID;
    @NonNull
    private UUID equipmentID;
    @NonNull
    private UUID visitorID;
    @NonNull
    private LocalDateTime data;
    private int duration;

    public Session(@NonNull UUID ID, @NonNull UUID equipmentID, @NonNull UUID visitorID, @NonNull LocalDateTime data, int duration) {
        this.ID = ID;
        this.equipmentID = equipmentID;
        this.visitorID = visitorID;
        this.data = data;
        this.duration = duration;
    }

    public Session(@NonNull UUID ID, @NonNull UUID equipmentID, @NonNull UUID visitorID, @NonNull LocalDateTime data) {
        this.ID = ID;
        this.equipmentID = equipmentID;
        this.visitorID = visitorID;
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
    public UUID getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(@NonNull UUID equipmentID) {
        this.equipmentID = equipmentID;
    }

    @NonNull
    public UUID getVisitorID() {
        return visitorID;
    }

    public void setVisitorID(@NonNull UUID visitorID) {
        this.visitorID = visitorID;
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
                ",\n  equipment_identifier=" + equipmentID +
                ",\n  visitor_identifier=" + visitorID +
                ",\n  data=" + data +
                ((duration == 0) ? "" : ",\n  duration=" + duration) +
                "};";
    }
}
