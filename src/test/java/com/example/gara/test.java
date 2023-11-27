package com.example.gara;

import com.example.gara.model.Accessory;
import com.example.gara.repository.AccessoryRepository;
import com.example.gara.repository.ResultSetQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
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
//        accessoryRepository.deleteAccessoryById(87360007);


//        statistic
//        List<ResultSetQuery> resultSetQueries = accessoryRepository.statistic("2022-10-01", "2022-11-01");
//        for (ResultSetQuery resultSetQuery : resultSetQueries){
//            System.out.println(resultSetQuery.getName());
//        }

//        usedDetail
//        List<ResultSetQuery> resultSetQueries = accessoryRepository.usedDetail(2,"2022-10-01", "2022-11-01");
//        for (ResultSetQuery resultSetQuery : resultSetQueries){
//            System.out.println(resultSetQuery.getDate().toString());
//        }

//        billdetail
//        List<ResultSetQuery> resultSetQueries = accessoryRepository.exportBill(2);
//        for (ResultSetQuery resultSetQuery : resultSetQueries){
//            System.out.println(resultSetQuery.getBilldate().toString());
//        }
//    }

//        import bill detail
        List<ResultSetQuery> resultSetQueries = accessoryRepository.importBillDetail(2);
        for (ResultSetQuery resultSetQuery : resultSetQueries){
            System.out.println(resultSetQuery.getDistributoradd());
        }
    }
}
