package com.example.gara.service;

import com.example.gara.model.Distributor;
import com.example.gara.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistributorService {
    List<Distributor> searchDistributorByKey(String key);

    Distributor getDistributorById(int id);

    void addDistributor(Member member);
}
