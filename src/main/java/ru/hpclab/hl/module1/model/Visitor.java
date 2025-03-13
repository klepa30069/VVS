package ru.hpclab.hl.module1.model;


import org.springframework.lang.NonNull;

import java.util.UUID;

// Посетитель
public class Visitor {

    @NonNull
    private UUID ID;
    @NonNull
    private String fio;
    private String subscription;
    private double weight;
    private double height;

    public Visitor(@NonNull UUID ID, @NonNull String fio, String subscription, double weight, double height) {
        this.ID = ID;
        this.fio = fio;
        this.subscription = subscription;
        this.weight = weight;
        this.height = height;
    }

    public Visitor(@NonNull UUID ID, @NonNull String fio) {
        this.ID = ID;
        this.fio = fio;
    }

    public Visitor() {
    }

    @NonNull
    public UUID getId() {
        return ID;
    }

    public void setId(@NonNull UUID ID) {
        this.ID = ID;
    }

    @NonNull
    public String getFio() {
        return fio;
    }

    public void setFio(@NonNull String fio) {
        this.fio = fio;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Visitor {\n" +
                "  identifier=" + ID +
                ",\n  fio='" + fio +
                "',\n  subscription='" + ((subscription == null) ? "None" : subscription) +
                ((weight == 0.0) ? "'" : "',\n  weight=" + weight) +
                ((height == 0.0) ? "" : ",\n  height=" + height) +
                "};";
    }
}
