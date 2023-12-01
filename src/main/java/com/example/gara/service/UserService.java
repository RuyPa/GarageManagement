package com.example.gara.service;

import com.example.gara.model.Member;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Member getAccount(String username);
}
