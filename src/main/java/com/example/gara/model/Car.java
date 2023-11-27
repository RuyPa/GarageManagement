package com.example.gara.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Car {
    private int id;
    private String name;
    private String tier;
    private String note;
    private Client client;

    public Car() {
    }

    public Car(int id, String name, String tier, String note, Client client) {
        this.id = id;
        this.name = name;
        this.tier = tier;
        this.note = note;
        this.client = client;
    }
}
