package com.example.gara.controller;


import com.example.gara.model.Accessory;
import com.example.gara.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
@RequiredArgsConstructor
public class AddController {
    final private AccessoryService accessoryService;

//    TODO: view
    @GetMapping("/addAccessoryView")
    public String addAccessoryView(){
        return "AddAccessoryView";
    }

//    TODO: action
    @Transactional
    @PostMapping("/addAccessory")
    public String addAccessory(@RequestParam("name")String name,
                               @RequestParam("price")int price,
                               @RequestParam("des")String des){
        Accessory accessory = new Accessory(new Random().nextInt(100000000),name, price, des);
        accessoryService.addAccessory(accessory);
        return "ManageAccessoryView";
    }
}
