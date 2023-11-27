package com.example.gara.model;

import java.util.Date;
import java.util.List;

public class ImportBill {
    private int id;
    private Date date;
    private int discount;
    private List<Accessory> accessories;
    private Employee employee;
    private Distributor distributor;

    public ImportBill(int id, Date date, int discount, List<Accessory> accessories, Employee employee, Distributor distributor) {
        this.id = id;
        this.date = date;
        this.discount = discount;
        this.accessories = accessories;
        this.employee = employee;
        this.distributor = distributor;
    }

    public ImportBill() {
    }
}
