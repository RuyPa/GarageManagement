package com.example.gara.controller;


import com.example.gara.model.*;
import com.example.gara.service.AccessoryService;
import com.example.gara.service.DistributorService;
import com.example.gara.service.ImportBillService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String searchDistributorView(HttpSession httpSession){
        List<ImportedAccessory> importedAccessories = new ArrayList<>();
        httpSession.setAttribute("importedAccessories", importedAccessories);
        httpSession.setAttribute("stt", 0);
        return "SearchDistributorView";
    }

    @GetMapping("/searchAccessoryToImportView/{id}")
    public String importAccessoryView(@PathVariable("id")int id,
                                      HttpSession httpSession){
        if(!ObjectUtils.isEmpty(httpSession.getAttribute("distributor")) ){
            httpSession.setAttribute("stt", 1);
        }
        Distributor distributor = distributorService.getDistributorById(id);
        httpSession.setAttribute("distributor", distributor);



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
        Member employee = (Member) httpSession.getAttribute("employee");

        model.addAttribute("importedAccessories", importedAccessories);
        model.addAttribute("distributor", distributor);
        model.addAttribute("employee", employee);

        int totalImport = 0;
        for(ImportedAccessory importedAccessory : importedAccessories){
            totalImport +=  importedAccessory.getQuantity() * importedAccessory.getPrice();
        }

        model.addAttribute("totalImport", totalImport);

        return "CreateBillView";
    }

    @GetMapping("/deleteImportAccessory/{name}")
    public String deleteCreateBillView(@PathVariable("name")String accessName,
                                       HttpSession httpSession){
        List<ImportedAccessory> importedAccessories = (List<ImportedAccessory>)httpSession.getAttribute("importedAccessories");
        for (ImportedAccessory importedAccessory : importedAccessories){
            if(importedAccessory.getAccessory().getName().equals(accessName)){
                importedAccessories.remove(importedAccessory);
                break;
            }
        }
        httpSession.setAttribute("importedAccessories", importedAccessories);
        return "redirect:/createBillView";
    }

    @GetMapping("/updateImportAccessoryView/{id}")
    public String updateCreateBillView(@PathVariable("id")String accessName,
                                       HttpSession httpSession,
                                       Model model){
        Accessory accessory = new Accessory();
        List<ImportedAccessory> importedAccessories = (List<ImportedAccessory>)httpSession.getAttribute("importedAccessories");
        for(ImportedAccessory importedAccessory : importedAccessories){
            if(importedAccessory.getAccessory().getName().equals(accessName)){
                accessory = importedAccessory.getAccessory();
            }
        }
        model.addAttribute("accessory", accessory);


//        List<ImportedAccessory> importedAccessories = (List<ImportedAccessory>)httpSession.getAttribute("importedAccessories");
//        for (ImportedAccessory importedAccessory : importedAccessories){
//            if(importedAccessory.getAccessory().getName().equals(accessName)){
//                importedAccessories.remove(importedAccessory);
//                break;
//            }
//        }
//        httpSession.setAttribute("importedAccessories", importedAccessories);
        return "UpdateInImportView";
    }

    @GetMapping("/addDistributorView")
    public String addDistributorView(Model model){
        Member member = new Member();
        model.addAttribute("member", member);
        return "AddDistributorView";
    }

//    TODO: action
    @GetMapping("/searchDistributor")
    public String searchDistributor(@RequestParam("key")String key,
                                    Model model,
                                    HttpSession httpSession){
        List<Distributor> distributorList = distributorService.searchDistributorByKey(key);
        model.addAttribute("distributors", distributorList);
        httpSession.setAttribute("distributorKey", key);
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
//        Employee employee = new Employee();
//        employee.setId(1);
        Member employee = (Member) httpSession.getAttribute("employee");
        Employee employee1 = new Employee();
        employee1.setId(employee.getId());
        employee1.setName(employee.getName());
        employee1.setDob(employee.getDob());
        employee1.setEmail(employee.getEmail());
        employee1.setPhonenumber(employee.getPhonenumber());
        employee1.setAddress(employee.getAddress());
        employee1.setUsername(employee.getUsername());
        employee1.setPassword(employee.getPassword());

        importBill.setEmployee(employee1);
        Date date = new Date();
        importBill.setDate(date);

        importBillService.saveImportBill(importBill);

        return "HomeManagerView";
    }

    @GetMapping("/updateImportAccessory/{id}")
    public String updateImportAccessoryy(HttpSession httpSession,
                                        @PathVariable("id") int id,
                                        @RequestParam("quantity")String quantity){
        List<ImportedAccessory> importedAccessories = (List<ImportedAccessory>)httpSession.getAttribute("importedAccessories");
        for(ImportedAccessory importedAccessory : importedAccessories){
            if(importedAccessory.getAccessory().getId() == id){
                importedAccessory.setQuantity(Integer.parseInt(quantity));
            }
        }
        httpSession.setAttribute("importedAccessories", importedAccessories);
        return "redirect:/createBillView";
    }

    @Transactional
    @PostMapping("/addDistributor")
    public String addDistributor(@ModelAttribute("member") Member member,
                                 HttpSession httpSession){

        String key = (String) httpSession.getAttribute("distributorKey");
        distributorService.addDistributor(member);

        return "redirect:/searchDistributor?key=" + key;
    }
}
