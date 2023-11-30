package com.example.gara.service;

import com.example.gara.model.ExportBill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExportBillService {
    List<ExportBill> getBillOfUsedAccessory(int id, String startTime, String endTime);
    ExportBill getExportBill(int usedId);
}
