package com.example.gara.service;

import com.example.gara.model.Distributor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistributorService {
    List<Distributor> searchDistributorByKey(String key);

    Distributor getDistributorById(int id);
}
