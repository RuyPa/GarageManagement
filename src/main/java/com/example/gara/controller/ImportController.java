package com.example.gara.controller;


import com.example.gara.model.*;
import com.example.gara.service.AccessoryService;
import com.example.gara.service.DistributorService;
import com.example.gara.service.ImportBillService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ImportController {

    private final DistributorService distributorService;
    private final AccessoryService accessoryService;
    private final ImportBillService importBillService;

//    TODO: view
    @GetMapping("/searchDitributorView")
    public String searchDistributorView(){
        return "SearchDistributorView";
    }

    @GetMapping("/searchAccessoryToImportView/{id}")
    public String importAccessoryView(@PathVariable("id")int id,
                                      HttpSession httpSession){
        Distributor distributor = distributorService.getDistributorById(id);
        httpSession.setAttribute("distributor", distributor);

        List<ImportedAccessory> importedAccessories = new ArrayList<>();
        httpSession.setAttribute("importedAccessories", importedAccessories);

        return "SearchAccessoryToImportView";
    }

    @GetMapping("/importView/{id}")
    public String importView(@PathVariable("id")int id,
                             HttpSession httpSession,
                             Model model){
        Accessory accessory = accessoryService.getAccessoryById(id);
        httpSession.setAttribute("importingAccessory", accessory);
        model.addAttribute("accessory", accessory);
        return "ImportAccessoryView";
    }

    @GetMapping("/createBillView")
    public String createBillView(HttpSession httpSession,
                                 Model model){

        List<ImportedAccessory> importedAccessories = (List<ImportedAccessory>)httpSession.getAttribute("importedAccessories");
        Distributor distributor = (Distributor) httpSession.getAttribute("distributor");


        model.addAttribute("importedAccessories", importedAccessories);
        model.addAttribute("distributor", distributor);

        int totalImport = 0;
        for(ImportedAccessory importedAccessory : importedAccessories){
            totalImport +=  importedAccessory.getQuantity() * importedAccessory.getPrice();
        }

        model.addAttribute("totalImport", totalImport);

        return "CreateBillView";
    }

//    TODO: action
    @GetMapping("/searchDistributor")
    public String searchDistributor(@RequestParam("key")String key,
                                    Model model){
        List<Distributor> distributorList = distributorService.searchDistributorByKey(key);
        model.addAttribute("distributors", distributorList);
        return "SearchDistributorView";
    }

    @GetMapping("/searchAccessoryToImport")
    public String searchAccessoryToImport(@RequestParam("key")String key,
                                            HttpSession httpSession,
                                            Model model){
        List<Accessory> accessories = accessoryService.searchAccessoryByKey(key);
        model.addAttribute("accessories", accessories);
        httpSession.setAttribute("key", key);

        return "SearchAccessoryToImportView";
    }

    @GetMapping("/importAccessory")
    public String importAccessory(@RequestParam("quantity")String quantity,
                                    HttpSession httpSession){
        List<ImportedAccessory> importedAccessories = (List<ImportedAccessory>) httpSession.getAttribute("importedAccessories");
        Accessory currentAccessory = (Accessory) httpSession.getAttribute("importingAccessory");

        ImportedAccessory importedAccessory = new ImportedAccessory();
        importedAccessory.setAccessory(currentAccessory);

        // Convert LocalDate to Date
        Date currentDateAsDate = new Date();
        importedAccessory.setDate(currentDateAsDate);
        importedAccessory.setQuantity(Integer.parseInt(quantity));
        importedAccessory.setPrice(currentAccessory.getPrice());
        importedAccessory.setId(new Random().nextInt(100000000));

        importedAccessories.add(importedAccessory);

//        System.out.println(importedAccessories.size());

        httpSession.setAttribute("importedAccessories", importedAccessories);

        String key = (String)httpSession.getAttribute("key");
        return "redirect:/searchAccessoryToImport?key=" + key;
    }

    @GetMapping("/saveImportBill")
    public String saveImportBill(HttpSession httpSession){


        ImportBill importBill = new ImportBill();

        List<ImportedAccessory> importedAccessories = (List<ImportedAccessory>)httpSession.getAttribute("importedAccessories");
        Distributor distributor = (Distributor) httpSession.getAttribute("distributor");

        importBill.setDistributor(distributor);
        importBill.setImportedAccessories(importedAccessories);
        importBill.setId(new Random().nextInt(100000000));
        importBill.setDiscount(0);
        Employee employee = new Employee();
        employee.setId(1);
        importBill.setEmployee(employee);
        Date date = new Date();
        importBill.setDate(date);

        importBillService.saveImportBill(importBill);

        return "HomeManagerView";
    }
}
