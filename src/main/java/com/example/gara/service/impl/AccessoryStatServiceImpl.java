package com.example.gara.service.impl;

import com.example.gara.model.AccessoryStat;
import com.example.gara.repository.AcceessoryStatRepository;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.AccessoryStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

            accessoryStat.setName(resultSetQuery.getName());

            if(ObjectUtils.isEmpty(resultSetQuery.getQuantity()) || resultSetQuery.getQuantity() == 0){
                accessoryStat.setQuantity(0);
                accessoryStat.setTotal(0);
            } else {
                accessoryStat.setQuantity(resultSetQuery.getQuantity());
                accessoryStat.setTotal(resultSetQuery.getTotal());
            }



            accessoryStats.add(accessoryStat);
        }

        return accessoryStats;
    }
}
