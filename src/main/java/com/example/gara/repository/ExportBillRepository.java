package com.example.gara.repository;

import com.example.gara.model.ExportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//SELECT
//        tblusedaccessory.date,
//        tblmember.name AS 'clientname',
//        tblcar.name AS 'carname',
//        tblaccessory.name AS 'accessname',
//        tblusedaccessory.price,
//        tblusedaccessory.quantity
//        FROM
//        tblusedaccessory, tblaccessory, tblfixedCar, tblcar, tblmember
//        WHERE
//        tblaccessory.id = 2
//        AND tblusedaccessory.accessory_id = tblaccessory.id
//        AND tblusedaccessory.date BETWEEN '2022-10-01' AND '2022-11-01'
//        AND tblusedaccessory.fixedcar_id = tblfixedCar.id
//        AND tblfixedCar.car_id = tblcar.id
//        AND tblcar.client_id = tblmember.id;
@Repository
public interface ExportBillRepository extends JpaRepository<ExportBill, Integer> {
//    @Query(value = "SELECT \n" +
//            "    tblusedaccessory.date,\n" +
//            "    tblmember.name as 'clientname',\n" +
//            "    tblcar.name AS 'carname',\n" +
//            "    tblaccessory.name AS 'accessname',\n" +
//            "    tblusedaccessory.price,\n" +
//            "    tblusedaccessory.quantity,\n" +
//            "    tblusedaccessory.id as usedid\n" +
//            "FROM \n" +
//            "    tblusedaccessory\n" +
//            "    LEFT JOIN tblaccessory ON tblusedaccessory.accessory_id = tblaccessory.id\n" +
//            "    LEFT JOIN tblfixedCar ON tblusedaccessory.fixedcar_id = tblfixedCar.id\n" +
//            "    LEFT JOIN tblcar ON tblfixedCar.car_id = tblcar.id\n" +
//            "    LEFT JOIN tblmember ON tblcar.client_id = tblmember.id\n" +
//            "WHERE \n" +
//            "    tblaccessory.id = :id\n" +
//            "    AND tblusedaccessory.date BETWEEN :startDay AND :endDay \n" +
//            "GROUP BY \n" +
//            "    tblusedaccessory.date,\n" +
//            "    tblmember.name,\n" +
//            "    tblcar.name,\n" +
//            "    tblaccessory.name,\n" +
//            "    tblusedaccessory.price,\n" +
//            "    tblusedaccessory.id,\n" +
//            "    tblusedaccessory.quantity;", nativeQuery = true)
    @Query(value = "SELECT\n" +
            "        tblusedaccessory.date,\n" +
            "        tblmember.name AS 'clientname',\n" +
            "        tblcar.name AS 'carname',\n" +
            "        tblaccessory.name AS 'accessname',\n" +
            "        tblusedaccessory.price,\n" +
            "        tblusedaccessory.quantity,\n" +
            "        tblusedaccessory.price * tblusedaccessory.quantity AS total, " +
            "        tblusedaccessory.id as usedid\n" +
            "        FROM\n" +
            "        tblusedaccessory, tblaccessory, tblfixedCar, tblcar, tblmember\n" +
            "        WHERE\n" +
            "        tblaccessory.id = :id\n" +
            "        AND tblusedaccessory.accessory_id = tblaccessory.id\n" +
            "        AND tblusedaccessory.date BETWEEN :startDay AND :endDay\n" +
            "        AND tblusedaccessory.fixedcar_id = tblfixedCar.id\n" +
            "        AND tblfixedCar.car_id = tblcar.id\n" +
            "        AND tblcar.client_id = tblmember.id;", nativeQuery = true)
    List<ResultSetQuery> getBillOfUsedAccessory(@Param("id") int id,
                                    @Param("startDay") String startDay,
                                    @Param("endDay")String endDay);

//    @Query(value =  "SELECT " +
//            "    tblexportbill.id, " +
//            "    tblexportbill.date AS billdate, " +
//            "    tblexportbill.payBy, " +
//            "    tblexportbill.saleOff, " +
//            "    tblmember.name AS employeename, " +
//            "    tblcar.name AS carname, " +
//            "    tblmember.name AS clientname, " +
//            "    tblaccessory.name AS accessname, " +
//            "    tblusedaccessory.quantity, " +
//            "    tblusedaccessory.price, " +
//            "    tblusedaccessory.date " +
//            "FROM " +
//            "    tblexportbill " +
//            "    LEFT JOIN tblfixedcar ON tblfixedcar.exportbill_id = tblexportbill.id " +
//            "    LEFT JOIN tblcar ON tblcar.id = tblfixedcar.car_id " +
//            "    LEFT JOIN tblmember ON tblcar.client_id = tblmember.id " +
//            "    LEFT JOIN tblusedaccessory ON tblusedaccessory.fixedcar_id = tblfixedcar.id " +
//            "    LEFT JOIN tblaccessory ON tblaccessory.id = tblusedaccessory.accessory_id " +
//            "WHERE " +
//            "    tblexportbill.id = ( " +
//            "        SELECT " +
//            "            tblexportbill.id " +
//            "        FROM " +
//            "            tblexportbill " +
//            "            LEFT JOIN tblfixedcar ON tblfixedcar.exportbill_id = tblexportbill.id " +
//            "            LEFT JOIN tblusedaccessory ON tblusedaccessory.fixedcar_id = tblfixedcar.id " +
//            "        WHERE " +
//            "            tblusedaccessory.id = :usedId " +
//            "    )", nativeQuery = true)
    @Query(value = "SELECT\n" +
            "    tblexportbill.id,\n" +
            "    tblexportbill.date AS billdate,\n" +
            "    tblexportbill.payBy,\n" +
            "    tblexportbill.saleOff,\n" +
            "    tblmember.name AS employeename,\n" +
            "    tblcar.name AS carname,\n" +
            "    tblmember.name AS clientname,\n" +
            "    tblaccessory.name AS accessname,\n" +
            "    tblusedaccessory.quantity,\n" +
            "    tblusedaccessory.price,\n" +
            "    tblusedaccessory.quantity * tblusedaccessory.price AS total," +
            "    tblusedaccessory.date\n" +
            "FROM\n" +
            "    tblexportbill, tblfixedcar, tblcar, tblmember, tblusedaccessory, tblaccessory\n" +
            "WHERE\n" +
            "    tblexportbill.id = :usedId\n" +
            "    AND tblfixedcar.exportbill_id = tblexportbill.id\n" +
            "    AND tblcar.id = tblfixedcar.car_id\n" +
            "    AND tblcar.client_id = tblmember.id\n" +
            "    AND tblusedaccessory.fixedcar_id = tblfixedcar.id\n" +
            "    AND tblaccessory.id = tblusedaccessory.accessory_id" , nativeQuery = true)
    List<ResultSetQuery> getExportBill(@Param("usedId")int usedId);

}
