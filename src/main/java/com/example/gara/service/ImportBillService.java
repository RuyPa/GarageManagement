package com.example.gara.service;

import com.example.gara.model.ImportBill;
import org.springframework.stereotype.Service;

@Service
public interface ImportBillService {
    ImportBill getImportBill(int usedId);

    void saveImportBill(ImportBill importBill);
}
