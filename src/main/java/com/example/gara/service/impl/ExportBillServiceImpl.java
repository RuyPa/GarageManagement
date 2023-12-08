package com.example.gara.service.impl;

import com.example.gara.model.*;
import com.example.gara.repository.ExportBillRepository;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.ExportBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
//            exportBill.setDate(resultSetQuery.getDate());

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
            usedAccessory.setId(resultSetQuery.getUsedid());
            usedAccessory.setPrice(resultSetQuery.getPrice());
            usedAccessory.setQuantity(resultSetQuery.getQuantity());
            usedAccessory.setDate(resultSetQuery.getDate());
            usedAccessory.setTotal(resultSetQuery.getTotal());
            accessories.add(usedAccessory);
            fixedCar.setUsedAccessories(accessories);
            fixedCars.add(fixedCar);
            exportBill.setFixedCars(fixedCars);


            exportBills.add(exportBill);


        }
        return exportBills;
    }

    @Override
    public ExportBill getExportBill(int usedId) {

        List<ResultSetQuery> resultSetQueries = exportBillRepository.getExportBill(usedId);

        ExportBill exportBill = new ExportBill();

        exportBill.setId(resultSetQueries.get(0).getId());
        exportBill.setDate(resultSetQueries.get(0).getBilldate());
        exportBill.setPayBy(resultSetQueries.get(0).getPayBy());
        exportBill.setSaleOff(resultSetQueries.get(0).getSaleOff());
        Employee employee = new Employee();
        employee.setName(resultSetQueries.get(0).getEmployeename());
        exportBill.setEmployee(employee);

        Client client = new Client();
        client.setName(resultSetQueries.get(0).getClientname());
        exportBill.setClient(client);

        List<FixedCar> fixedCars = new ArrayList<>();

        for(ResultSetQuery resultSetQuery : resultSetQueries){
            if(!ObjectUtils.isEmpty(resultSetQuery.getQuantity())){
                FixedCar fixedCar = new FixedCar();

                Car car = new Car();
                car.setName(resultSetQuery.getCarname());
                Client client1 = new Client();
                client1.setName(resultSetQuery.getClientname());

                car.setClient(client1);


                fixedCar.setCar(car);

                UsedAccessory usedAccessory = new UsedAccessory();

                usedAccessory.setQuantity(resultSetQuery.getQuantity());
                usedAccessory.setPrice(resultSetQuery.getPrice());
                usedAccessory.setTotal(resultSetQuery.getTotal());
                usedAccessory.setDate(resultSetQuery.getDate());


                Accessory accessory = new Accessory();
                accessory.setName(resultSetQuery.getAccessname());
                usedAccessory.setAccessory(accessory);

                List<UsedAccessory> usedAccessories = new ArrayList<>();
                usedAccessories.add(usedAccessory);

                fixedCar.setUsedAccessories(usedAccessories);

                fixedCars.add(fixedCar);
            }

        }
        exportBill.setFixedCars(fixedCars);

        return exportBill;
    }
}
