package com.example.gara.service.impl;

import com.example.gara.model.*;
import com.example.gara.repository.ImportBillRepository;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.ImportBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportBillServiceImpl implements ImportBillService {
    final private ImportBillRepository importBillRepository;
    @Override
    public ImportBill getImportBill(int usedId) {
        ImportBill importBill = new ImportBill();

        List<ResultSetQuery> resultSetQueries = importBillRepository.getImportBill(usedId);

        importBill.setId(resultSetQueries.get(0).getId());
        importBill.setDate(resultSetQueries.get(0).getImportdate());
        Distributor distributor = new Distributor();
        distributor.setName(resultSetQueries.get(0).getDistributorname());
        distributor.setAddress(resultSetQueries.get(0).getDistributoradd());
        importBill.setDistributor(distributor);

        Employee employee = new Employee();
        employee.setName(resultSetQueries.get(0).getEmployeename());
        importBill.setEmployee(employee);

        List<ImportedAccessory> importedAccessories = new ArrayList<>();

        for(ResultSetQuery resultSetQuery : resultSetQueries){
            ImportedAccessory importedAccessory = new ImportedAccessory();
            importedAccessory.setDate(resultSetQuery.getDate());
            importedAccessory.setQuantity(resultSetQuery.getQuantity());
            importedAccessory.setPrice(resultSetQuery.getPrice());
            Accessory accessory = new Accessory();
            accessory.setName(resultSetQuery.getAccessname());
            importedAccessory.setAccessory(accessory);
            importedAccessories.add(importedAccessory);
        }

        importBill.setImportedAccessories(importedAccessories);



        return importBill;
    }

    @Transactional
    @Override
    public void saveImportBill(ImportBill importBill) {
        importBillRepository.createImportBill(importBill);
        for(ImportedAccessory importedAccessory : importBill.getImportedAccessories()){
            importBillRepository.insertImportedAccessory(importedAccessory, importBill.getId());
        }

    }
}
