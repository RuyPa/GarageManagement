package com.example.gara;

import com.example.gara.model.Accessory;
import com.example.gara.repository.AccessoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class test {
    @Autowired
    AccessoryRepository accessoryRepository;

    @Test
    public void thichtest(){
//        get by search
//        System.out.println(accessoryRepository.searchAccessoryByKey("lop").size());


//            insert
//        Accessory accessory = new Accessory(new Random().nextInt(100000000),"lop xe SUV", 321, "");
//
//        accessoryRepository.insertAccessory(accessory);

//        update

//        Accessory accessory = accessoryRepository.getReferenceById(1);
//
//        accessory.setName("lop xe SUV");
//
//        accessoryRepository.updateAccessory(accessory);

//        delete
        accessoryRepository.deleteAccessoryById(87360007);

    }
}
