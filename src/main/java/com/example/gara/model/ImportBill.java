package com.example.gara.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "tblimportbill")
@Entity
@Getter
@Setter
public class ImportBill {
    @Id
    private int id;
    private Date date;
    private int discount;
    @Transient
    private List<ImportedAccessory> importedAccessories;
    @Transient
    private Employee employee;
    @Transient
    private Distributor distributor;


    public ImportBill() {
    }



}
