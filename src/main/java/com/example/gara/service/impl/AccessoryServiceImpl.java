package com.example.gara.service.impl;

import com.example.gara.model.Accessory;
import com.example.gara.repository.AccessoryRepository;
import com.example.gara.service.AccessoryService;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {
    private final AccessoryRepository accessoryRepository;
    @Override
    public boolean addAccessory(Accessory accessory) {
        accessoryRepository.insertAccessory(accessory);
        return true;
    }

    @Override
    public List<Accessory> searchAccessoryByKey(String key) {
        return accessoryRepository.searchAccessoryByKey(key);
    }

    @Override
    public boolean updateAccessory(Accessory accessory) {
        accessoryRepository.updateAccessory(accessory);
        return true;
    }

    @Override
    public boolean deleteAccessory(int id) {
        accessoryRepository.deleteAccessoryById(id);
        return true;
    }
}
