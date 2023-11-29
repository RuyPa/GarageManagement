package com.example.gara.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "tblexportbill")
@Entity
public class ExportBill {
    @Id
    private int id;
    private Date date;
    private int saleOff;
    private String payBy;
    @Transient
    private Car car;
    @Transient
    private Client client;
    @Transient
    private List<FixedCar> fixedCars;
    @Transient
    private Employee employee;

    public ExportBill(int id, Date date, int saleOff, String payBy, List<FixedCar> fixedCars, Employee employee) {
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
