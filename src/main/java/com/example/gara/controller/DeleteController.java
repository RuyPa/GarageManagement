package com.example.gara.controller;

import com.example.gara.model.Accessory;
import com.example.gara.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DeleteController {
    private final AccessoryService accessoryService;

//    TODO: view
    @GetMapping("/deleteAccessoryView/{id}")
    public String deleteAccessoryView(@PathVariable("id")int id,
                                      Model model){
        Accessory accessory = accessoryService.getAccessoryById(id);
        model.addAttribute("accessory", accessory);
        return "DeleteAccessoryView";
    }

//    TODO: action
    @Transactional
    @PostMapping("/deleteAccessory")
    public String updateAccessory(@RequestParam("id")int id){
        accessoryService.deleteAccessory(id);
        return "ManageAccessoryView";
    }
}
