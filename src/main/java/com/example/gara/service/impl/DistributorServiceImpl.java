package com.example.gara.service.impl;

import com.example.gara.model.Distributor;
import com.example.gara.model.Member;
import com.example.gara.repository.DistributorRepsitory;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.DistributorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DistributorServiceImpl implements DistributorService {
    private final DistributorRepsitory distributorRepsitory;
    @Override
    public List<Distributor> searchDistributorByKey(String key) {

        List<ResultSetQuery> resultSetQueries = distributorRepsitory.getDistributorByKey(key);

        List<Distributor> distributors = new ArrayList<>();

        for(ResultSetQuery resultSetQuery : resultSetQueries){
            Distributor distributor = new Distributor();
            distributor.setId(resultSetQuery.getId());
            distributor.setAddress(resultSetQuery.getAddress());
            distributor.setName(resultSetQuery.getName());
            distributor.setPhonenumber(resultSetQuery.getPhonenumber());
            distributors.add(distributor);
        }

        return distributors;
    }

    @Override
    public Distributor getDistributorById(int id) {
        Distributor distributor = new Distributor();
        ResultSetQuery resultSetQuery = distributorRepsitory.getDistributorById(id);
        distributor.setId(resultSetQuery.getId());
        distributor.setDob(resultSetQuery.getDob());
        distributor.setPhonenumber(resultSetQuery.getPhonenumber());
        distributor.setAddress(resultSetQuery.getAddress());
        distributor.setEmail(resultSetQuery.getEmail());
        distributor.setName(resultSetQuery.getName());
        return distributor;
    }

    @Override
    public void addDistributor(Member member) {
        member.setUsername("");
        member.setPassword("");
        member.setId(new Random().nextInt(100000000));
        distributorRepsitory.insertMember(member);
        distributorRepsitory.insertDistributor(member);
    }
}
