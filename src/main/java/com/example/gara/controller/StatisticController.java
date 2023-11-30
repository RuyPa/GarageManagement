package com.example.gara.controller;

import com.example.gara.model.*;
import com.example.gara.service.AccessoryStatService;
import com.example.gara.service.ExportBillService;
import com.example.gara.service.ImportBillService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StatisticController {

    final private AccessoryStatService accessoryStatService;
    final private ExportBillService exportBillService;
    final private ImportBillService importBillService;
    @GetMapping("/statisticView")
    public String statisticView(){
        return "StatisticView";
    }

    @GetMapping("/statisticView/statistic")
    public String statistic(@RequestParam("startTime")String starTime,
                            @RequestParam("endTime")String endTime,
                            HttpSession HttpSession,
                            Model model){

        List<AccessoryStat> accessoryStats = accessoryStatService.statisticAccessory(starTime, endTime);
        model.addAttribute("accessoryStats", accessoryStats);

        HttpSession.setAttribute("startTime", starTime);
        HttpSession.setAttribute("endTime", endTime);

        return "StatisticView";
    }

    @GetMapping("/usedDetailView/{id}")
    public String usedDetailView(@PathVariable("id")int id,
                                 Model model,
                                 HttpSession httpSession){

        String starTime = (String) httpSession.getAttribute("startTime");
        String endTime = (String) httpSession.getAttribute("endTime");

        List<ExportBill> exportBills = exportBillService.getBillOfUsedAccessory(id, starTime, endTime);

        model.addAttribute("exportBills", exportBills);

        return "UsedDetailView";
    }

    @GetMapping("/billDetail/{id}")
    public String billDetail(@PathVariable("id")int id,
                            Model model){

        ExportBill exportBill = exportBillService.getExportBill(id);
        int total = 0;
        for(FixedCar fixedCar : exportBill.getFixedCars()){
            total += fixedCar.getUsedAccessories().get(0).getPrice() * fixedCar.getUsedAccessories().get(0).getQuantity();
        }


        ImportBill importBill = importBillService.getImportBill(id);
        int totalImport = 0;
        for (ImportedAccessory importedAccessory : importBill.getImportedAccessories()){
            totalImport += importedAccessory.getQuantity() * importedAccessory.getPrice();
        }

        model.addAttribute("exportBill", exportBill);
        model.addAttribute("total", total);

        model.addAttribute("importBill", importBill);
        model.addAttribute("totalImport", totalImport);
        return "BillDetailView";
    }

}
