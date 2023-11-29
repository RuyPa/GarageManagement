package com.example.gara.repository;

import com.example.gara.model.ExportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportBillRepository extends JpaRepository<ExportBill, Integer> {
    @Query(value = "SELECT \n" +
            "    tblusedaccessory.date,\n" +
            "    tblmember.name as 'clientname',\n" +
            "    tblcar.name AS 'carname',\n" +
            "    tblaccessory.name AS 'accessname',\n" +
            "    tblusedaccessory.price,\n" +
            "    tblusedaccessory.quantity\n" +
            "FROM \n" +
            "    tblusedaccessory\n" +
            "    LEFT JOIN tblaccessory ON tblusedaccessory.accessory_id = tblaccessory.id\n" +
            "    LEFT JOIN tblfixedCar ON tblusedaccessory.fixedcar_id = tblfixedCar.id\n" +
            "    LEFT JOIN tblcar ON tblfixedCar.car_id = tblcar.id\n" +
            "    LEFT JOIN tblmember ON tblcar.client_id = tblmember.id\n" +
            "WHERE \n" +
            "    tblaccessory.id = :id\n" +
            "    AND tblusedaccessory.date BETWEEN :startDay AND :endDay\n" +
            "GROUP BY \n" +
            "    tblusedaccessory.date,\n" +
            "    tblmember.name,\n" +
            "    tblcar.name,\n" +
            "    tblaccessory.name,\n" +
            "    tblusedaccessory.price,\n" +
            "    tblusedaccessory.quantity;", nativeQuery = true)
    List<ResultSetQuery> getBillOfUsedAccessory(@Param("id") int id,
                                    @Param("startDay") String startDay,
                                    @Param("endDay")String endDay);

    @Query(value =  "SELECT " +
            "    tblexportbill.id, " +
            "    tblexportbill.date AS billdate, " +
            "    tblexportbill.payBy, " +
            "    tblexportbill.saleOff, " +
            "    tblmember.name AS employeename, " +
            "    tblcar.name AS carname, " +
            "    tblmember.name AS clientname, " +
            "    tblaccessory.name AS accessname, " +
            "    tblusedaccessory.quantity, " +
            "    tblusedaccessory.price, " +
            "    tblusedaccessory.date " +
            "FROM " +
            "    tblexportbill " +
            "    LEFT JOIN tblfixedcar ON tblfixedcar.exportbill_id = tblexportbill.id " +
            "    LEFT JOIN tblcar ON tblcar.id = tblfixedcar.car_id " +
            "    LEFT JOIN tblmember ON tblcar.client_id = tblmember.id " +
            "    LEFT JOIN tblusedaccessory ON tblusedaccessory.fixedcar_id = tblfixedcar.id " +
            "    LEFT JOIN tblaccessory ON tblaccessory.id = tblusedaccessory.accessory_id " +
            "WHERE " +
            "    tblexportbill.id = ( " +
            "        SELECT " +
            "            tblexportbill.id " +
            "        FROM " +
            "            tblexportbill " +
            "            LEFT JOIN tblfixedcar ON tblfixedcar.exportbill_id = tblexportbill.id " +
            "            LEFT JOIN tblusedaccessory ON tblusedaccessory.fixedcar_id = tblfixedcar.id " +
            "        WHERE " +
            "            tblusedaccessory.id = :usedId " +
            "    )", nativeQuery = true)
    List<ResultSetQuery> getExportBill(@Param("usedId")int usedId);

}
