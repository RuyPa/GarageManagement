package com.example.gara.service.impl;

import com.example.gara.model.AccessoryStat;
import com.example.gara.repository.AcceessoryStatRepository;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.AccessoryStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccessoryStatServiceImpl implements AccessoryStatService {

    private final AcceessoryStatRepository acceessoryStatRepository;
    @Override
    public List<AccessoryStat> statisticAccessory(String startTime, String endTime) {
        List<ResultSetQuery> resultSetQueries = acceessoryStatRepository.statisticAccessory(startTime, endTime);

        List<AccessoryStat> accessoryStats = new ArrayList<>();
        for(ResultSetQuery resultSetQuery : resultSetQueries){

            AccessoryStat accessoryStat = new AccessoryStat();

            accessoryStat.setId(resultSetQuery.getId());
            accessoryStat.setQuantity(resultSetQuery.getQuantity());
            accessoryStat.setName(resultSetQuery.getName());
            accessoryStat.setPrice(resultSetQuery.getPrice());

            accessoryStats.add(accessoryStat);
        }

        return accessoryStats;
    }
}
