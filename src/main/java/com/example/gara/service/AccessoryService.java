package com.example.gara.service;

import com.example.gara.model.Accessory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccessoryService {
    boolean addAccessory(Accessory accessory);
    List<Accessory> searchAccessoryByKey(String key);
    boolean updateAccessory(Accessory accessory);
    boolean deleteAccessory(int id);
}
