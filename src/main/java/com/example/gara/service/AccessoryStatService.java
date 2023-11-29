package com.example.gara.service;

import com.example.gara.model.AccessoryStat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AccessoryStatService {
    List<AccessoryStat> statisticAccessory(String startTime, String endTime);
}
