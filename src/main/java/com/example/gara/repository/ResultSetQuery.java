package com.example.gara.repository;

import java.util.Date;

public interface ResultSetQuery {
    Integer getId();
    String getName();
    Integer getQuantity();
    Integer getPrice();
    Date getDate();
    String getClientname();
    String getCarname();
    String getAccessname();
    Date getBilldate();
    String getPayBy();
    Integer getSaleOff();
    String getEmployeename();
    String getDistributoradd();
    String getDistributorname();
    Date getImportdate();
    Integer getDiscount();
}
