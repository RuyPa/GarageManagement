package com.example.gara.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FixecCar {
    private int id;
    private Date startTime;
    private Date endTime;
    private Car car;
    private List<UsedAccessory> usedAccessories;

    public FixecCar(int id, Date startTime, Date endTime, Car car, List<UsedAccessory> usedAccessories) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.car = car;
        this.usedAccessories = usedAccessories;
    }

    public FixecCar() {
    }
}
