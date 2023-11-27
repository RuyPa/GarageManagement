package com.example.gara;

import com.example.gara.repository.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GaraApplication {
    @Autowired AccessoryRepository accessoryRepository;
    public static void main(String[] args) {
        SpringApplication.run(GaraApplication.class, args);
    }
}
