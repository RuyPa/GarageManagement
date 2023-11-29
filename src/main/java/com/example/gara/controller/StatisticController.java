package com.example.gara.controller;

import com.example.gara.model.AccessoryStat;
import com.example.gara.service.AccessoryStatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StatisticController {

    final private AccessoryStatService accessoryStatService;
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

    @GetMapping("/usedDetailView")
    public String usedDetailView(@RequestParam("id")int id,
                                 HttpSession httpSession){

        String starTime = (String) httpSession.getAttribute("startTime");
        String endTime = (String) httpSession.getAttribute("endTime");



        return "UsedDetailView";
    }

}
