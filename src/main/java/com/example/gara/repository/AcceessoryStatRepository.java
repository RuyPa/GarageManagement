package com.example.gara.repository;

import com.example.gara.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceessoryStatRepository extends JpaRepository<Accessory, Integer> {
    @Query(value = "SELECT\n" +
            "    tblaccessory.id,\n" +
            "    tblaccessory.name,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            SUM(tblusedaccessory.quantity)\n" +
            "        FROM\n" +
            "            tblusedaccessory\n" +
            "        WHERE\n" +
            "            tblusedaccessory.accessory_id = tblaccessory.id\n" +
            "            AND tblusedaccessory.date BETWEEN :startDay AND :endDay\n" +
            "        GROUP BY tblusedaccessory.accessory_id\n" +
            "    ) as quantity,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            SUM(tblusedaccessory.quantity * tblusedaccessory.price)\n" +
            "        FROM\n" +
            "            tblusedaccessory\n" +
            "        WHERE\n" +
            "            tblusedaccessory.accessory_id = tblaccessory.id\n" +
            "            AND tblusedaccessory.date BETWEEN :startDay AND :endDay\n" +
            "        GROUP BY tblusedaccessory.accessory_id\n" +
            "    ) as total\n" +
            "FROM tblaccessory\n" +
            "ORDER BY total DESC, quantity DESC;", nativeQuery = true)
    List<ResultSetQuery> statisticAccessory(@Param("startDay") String startDay,
                                   @Param("endDay")String endDay);


}
