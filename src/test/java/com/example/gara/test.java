package com.example.gara;

import com.example.gara.model.Accessory;
import com.example.gara.repository.AcceessoryStatRepository;
import com.example.gara.repository.AccessoryRepository;
import com.example.gara.repository.ExportBillRepository;
import com.example.gara.repository.ResultSetQuery;
import com.example.gara.service.AccessoryStatService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class test {
    @Autowired
    AccessoryRepository accessoryRepository;

    @Autowired
    ExportBillRepository exportBillRepository;

    @Test
    public void thichtest() throws ParseException {
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
//        List<ResultSetQuery> resultSetQueries = acceessoryStatRepository.statisticAccessory("2022-10-01", "2022-11-01");
//        for (ResultSetQuery resultSetQuery : resultSetQueries){
//            System.out.println(resultSetQuery.getName());
//        }

//        usedDetail
//        List<ResultSetQuery> resultSetQueries = accessoryRepository.usedDetail(2,"2022-10-01", "2022-11-01");
//        for (ResultSetQuery resultSetQuery : resultSetQueries){
//            System.out.println(resultSetQuery.getDate().toString());
//        }

//        billdetail
//        List<ResultSetQuery> resultSetQueries = exportBillRepository.getExportBill(2);
//        for (ResultSetQuery resultSetQuery : resultSetQueries){
//            System.out.println(resultSetQuery.getQuantity());
//        }
//    }

//        import bill detail
//        List<ResultSetQuery> resultSetQueries = accessoryRepository.importBillDetail(2);
//        for (ResultSetQuery resultSetQuery : resultSetQueries){
//            System.out.println(resultSetQuery.getDistributoradd());
//        }

        Date currentDateAsDate = new Date();

        // Define the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the date
        String formattedDate = dateFormat.format(currentDateAsDate);
        System.out.println(formattedDate);
    }

}
