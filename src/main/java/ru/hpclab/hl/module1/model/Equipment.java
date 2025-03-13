package ru.hpclab.hl.module1.model;


import org.springframework.lang.NonNull;

import java.util.UUID;

// Тренажер
public class Equipment {

    @NonNull
    private UUID id;
    @NonNull
    private String type; // "беговая дорожка" или "велосипед"
    @NonNull
    private boolean status;

    public Equipment(@NonNull UUID identifier, @NonNull String type, @NonNull boolean status) {
        this.id = identifier;
        this.type = type;
        this.status = status;
    }

    public Equipment() {
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public boolean getStatus() {
        return status;
    }

    public void setStatus(@NonNull boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Equipment {\n" +
                "  identifier=" + id +
                ",\n  type='" + type +
                "',\n  is =" + status +
                "};";
    }
}
