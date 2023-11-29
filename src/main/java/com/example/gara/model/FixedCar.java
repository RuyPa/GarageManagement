package com.example.gara.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FixedCar {
    private int id;
    private Date startTime;
    private Date endTime;
    private Car car;
    private List<UsedAccessory> usedAccessories;

    public FixedCar(int id, Date startTime, Date endTime, Car car, List<UsedAccessory> usedAccessories) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.car = car;
        this.usedAccessories = usedAccessories;
    }

    public FixedCar() {
    }
}
