package com.example.gara.service.impl;

import com.example.gara.model.Member;
import com.example.gara.repository.DistributorRepsitory;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final DistributorRepsitory distributorRepsitory;
    @Override
    public Member getAccount(String username) {
        ResultSetQuery resultSetQuery = distributorRepsitory.getAccount(username);

        Member member = new Member();
        member.setId(resultSetQuery.getId());
        member.setName(resultSetQuery.getName());
        member.setDob(resultSetQuery.getDob());
        member.setEmail(resultSetQuery.getEmail());
        member.setPhonenumber(resultSetQuery.getPhonenumber());
        member.setAddress(resultSetQuery.getAddress());
        member.setUsername(resultSetQuery.getUsername());
        member.setPassword(resultSetQuery.getPassword());

        return member;
    }
}
