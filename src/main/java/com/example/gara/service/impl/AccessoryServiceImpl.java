package com.example.gara.service.impl;

import com.example.gara.model.Accessory;
import com.example.gara.repository.AccessoryRepository;
import com.example.gara.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
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

    @Override
    public Accessory getAccessoryById(int id) {
        return accessoryRepository.getById(id);
    }
}
