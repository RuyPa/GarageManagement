package com.example.gara.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ImportedAccessory {
    private int id;
    private Date date;
    private int quantity;
    private int price;
    private Accessory accessory;

    public ImportedAccessory(int id, Date date, int quantity, int price, Accessory accessory) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.accessory = accessory;
    }

    public ImportedAccessory() {
    }
}
