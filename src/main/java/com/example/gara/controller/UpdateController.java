package com.example.gara.controller;

import com.example.gara.model.Accessory;
import com.example.gara.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UpdateController {

    private final AccessoryService accessoryService;

//   TODO: vờ iu viu
    @GetMapping("/homeManagerView")
    public String homeManagerView(){
        return "HomeManagerView";
    }

    @GetMapping("/manageAccessory")
    public String manageAccessoryView(){
        return "ManageAccessoryView";
    }

    @GetMapping("/searchAccessoryView")
    public String searchAccessoryView(){
        return "SearchAccessoryView";
    }

    @GetMapping("/updateAccessoryView/{id}")
    public String updateAccessoryView(@PathVariable("id")int id,
                                      Model model){
        Accessory accessory = accessoryService.getAccessoryById(id);
        model.addAttribute("accessory", accessory);
        return "UpdateAccessoryView";
    }




//  TODO:  ách sừn
    @GetMapping("/searchAccessoryView/search")
    public String searchAccessory(@RequestParam("key")String key,
                                  Model model){
        List<Accessory> accessories = accessoryService.searchAccessoryByKey(key);
        model.addAttribute("accessories", accessories);
        return "SearchAccessoryView";
    }

    @Transactional
    @PostMapping("/updateAccessory")
    public String updateAccessory(@ModelAttribute("accessory") Accessory accessory){
        accessoryService.updateAccessory(accessory);
        return "ManageAccessoryView";
    }


}
