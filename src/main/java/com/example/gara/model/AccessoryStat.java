package com.example.gara.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessoryStat extends Accessory{
    private int quantity;
    private int revenue;

    public AccessoryStat(int quantity, int revenue) {
        this.quantity = quantity;
        this.revenue = revenue;
    }

    public AccessoryStat(int id, String name, int price, String des, int quantity, int revenue) {
        super(id, name, price, des);
        this.quantity = quantity;
        this.revenue = revenue;
    }

    public AccessoryStat() {
    }
}
