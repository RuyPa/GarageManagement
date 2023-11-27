package com.example.gara.model;

import java.util.Date;
import java.util.List;

public class ExportBill {
    private int id;
    private Date date;
    private int saleOff;
    private String payBy;
    private List<FixecCar> fixedCars;
    private Employee employee;

    public ExportBill(int id, Date date, int saleOff, String payBy, List<FixecCar> fixedCars, Employee employee) {
        this.id = id;
        this.date = date;
        this.saleOff = saleOff;
        this.payBy = payBy;
        this.fixedCars = fixedCars;
        this.employee = employee;
    }

    public ExportBill() {
    }
}
