package com.example.gara.repository;

import com.example.gara.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceessoryStatRepository extends JpaRepository<Accessory, Integer> {
    @Query(value = "SELECT "
            + "tblaccessory.id, "
            + "tblaccessory.name, "
            + "SUM(tblusedaccessory.quantity) as 'quantity', "
            + "tblusedaccessory.price "
            + "FROM "
            + "tblaccessory "
            + "INNER JOIN tblusedaccessory ON tblaccessory.id = tblusedaccessory.accessory_id "
            + "WHERE "
            + "tblusedaccessory.date BETWEEN :startDay AND :endDay "
            + "GROUP BY "
            + "tblaccessory.id, "
            + "tblaccessory.name, "
            + "tblusedaccessory.price", nativeQuery = true)
    List<ResultSetQuery> statisticAccessory(@Param("startDay") String startDay,
                                   @Param("endDay")String endDay);


}
