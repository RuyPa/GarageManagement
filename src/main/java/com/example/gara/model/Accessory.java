package com.example.gara.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblaccessory")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "des")
    private String des;

    public Accessory() {
    }

    public Accessory(int id, String name, int price, String des) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public Accessory(String name, int price, String des) {
        this.name = name;
        this.price = price;
        this.des = des;
    }
}
