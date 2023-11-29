package com.example.gara.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.Date;
import java.util.List;

@Table(name = "tblimportbill")
@Entity
public class ImportBill {
    @Id
    private int id;
    private Date date;
    private int discount;
    @Transient
    private List<Accessory> accessories;
    @Transient
    private Employee employee;
    @Transient
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
