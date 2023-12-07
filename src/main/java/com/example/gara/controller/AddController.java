package com.example.gara.controller;


import com.example.gara.model.Accessory;
import com.example.gara.service.AccessoryService;
import com.example.gara.service.AccessoryStatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class AddController {
    final private AccessoryService accessoryService;
    final private AccessoryStatService accessoryStatService;

//    TODO: view
    @GetMapping("/addAccessoryView")
    public String addAccessoryView(){
//        System.out.println(accessoryStatService.statisticAccessory("2022-10-10", "2022-12-12").size());
        return "AddAccessoryView";
    }

//    TODO: action
    @Transactional
    @PostMapping("/addAccessory")
    public String addAccessory(@RequestParam("name")String name,
                               @RequestParam("price")int price,
                               @RequestParam("des")String des,
                               HttpSession httpSession){
        Accessory accessory = new Accessory(new Random().nextInt(100000000),name, price, des);
        accessoryService.addAccessory(accessory);
        if(!ObjectUtils.isEmpty(httpSession.getAttribute("key"))){
            List<Accessory> accessories = accessoryService.searchAccessoryByKey((String) httpSession.getAttribute("key"));
            httpSession.setAttribute("accessories", accessories);
            return "redirect:/moveFromChooseToBillDetail";
        }
        return "ManageAccessoryView";
    }
}
