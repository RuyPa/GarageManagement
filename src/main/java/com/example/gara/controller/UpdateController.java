package com.example.gara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpdateController {

    @GetMapping("/homeManagerView")
    public String homeManagerView(){
        return "HomeManagerView";
    }

    @GetMapping("/searchAccessoryView")
    public String searchAccessoryView(){
        return "SearchAccessoryView";
    }

//    @GetMapping("/updateView/{id}")
//    public String updateView

}
