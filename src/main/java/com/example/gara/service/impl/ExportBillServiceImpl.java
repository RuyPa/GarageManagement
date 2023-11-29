package com.example.gara.service.impl;

import com.example.gara.model.*;
import com.example.gara.repository.ExportBillRepository;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.ExportBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportBillServiceImpl implements ExportBillService {
    private final ExportBillRepository exportBillRepository;
    @Override
    public List<ExportBill> getBillOfUsedAccessory(int id, String startTime, String endTime) {

        List<ResultSetQuery> resultSetQueries = exportBillRepository.getBillOfUsedAccessory(id, startTime, endTime);
        List<ExportBill> exportBills = new ArrayList<>();

        for (ResultSetQuery resultSetQuery : resultSetQueries){
            ExportBill exportBill = new ExportBill();
            exportBill.setDate(resultSetQuery.getDate());

            Client client = new Client();
            client.setName(resultSetQuery.getClientname());
            exportBill.setClient(client);

            Car car = new Car();
            car.setName(resultSetQuery.getCarname());
            exportBill.setCar(car);

            List<FixedCar> fixedCars = new ArrayList<>();
            FixedCar fixedCar = new FixedCar();
            List<UsedAccessory> accessories = new ArrayList<>();
            UsedAccessory usedAccessory = new UsedAccessory();
            Accessory accessory = new Accessory();
            accessory.setName(resultSetQuery.getAccessname());
            usedAccessory.setAccessory(accessory);
            usedAccessory.setPrice(resultSetQuery.getPrice());
            usedAccessory.setQuantity(resultSetQuery.getQuantity());
            accessories.add(usedAccessory);
            fixedCar.setUsedAccessories(accessories);
            fixedCars.add(fixedCar);
            exportBill.setFixedCars(fixedCars);


            exportBills.add(exportBill);


        }
        return exportBills;
    }
}
