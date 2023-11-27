package com.example.gara.repository;

import com.example.gara.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Integer>{
    @Query(value = "SELECT * FROM gara.tblaccessory WHERE tblaccessory.name LIKE %:key%", nativeQuery = true)
    List<Accessory> searchAccessoryByKey(@Param("key") String key);

    @Modifying
    @Query(value = "INSERT INTO tblaccessory (id, name, price, des) " + "VALUES (:#{#accessory.id}, :#{#accessory.name}, :#{#accessory.price}, :#{#accessory.des})", nativeQuery = true)
    void insertAccessory(@Param("accessory") Accessory accessory);

    @Modifying
    @Query(value = "UPDATE tblaccessory SET name = :#{#accessory.name}, price = :#{#accessory.price}, des = :#{#accessory.des} WHERE id = :#{#accessory.id}", nativeQuery = true)
    void updateAccessory(@Param("accessory") Accessory accessory);

    @Query(value = "SELECT * FROM gara.tblaccessory WHERE tblaccessory.id = :id", nativeQuery = true)
    Accessory getAccessoryById(@Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM gara.tblaccessory WHERE tblaccessory.id = :id", nativeQuery = true)
    void deleteAccessoryById(@Param("id") int id);

    @Query(value = "SELECT tblaccessory.id, tblaccessory.name, SUM(tblusedaccessory.quantity) as 'quantity', tblusedaccessory.price \n" +
            "FROM tblaccessory INNER JOIN tblusedaccessory ON tblaccessory.id= tblusedaccessory.accessory_id \n" +
            "WHERE tblusedaccessory.date BETWEEN :startDay AND :endDay GROUP BY tblaccessory.id, tblaccessory.name,tblusedaccessory.price", nativeQuery = true)
    List<ResultSetQuery> statistic(@Param("startDay") String startDay,
                                   @Param("endDay")String endDay);

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
    List<ResultSetQuery> usedDetail(@Param("id") int id,
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
    List<ResultSetQuery> exportBill(@Param("usedId")int usedId);

    @Query(value =  "SELECT " +
                    "    tblimportbill.id, " +
                    "    tblimportbill.date AS importdate, " +
                    "    tblimportbill.discount, " +
                    "    distributor.name AS distributorname, " +
                    "    distributor.address AS distributoradd, " +
                    "    employee.name AS employeename, " +
                    "    tblimportedaccessory.date, " +
                    "    tblimportedaccessory.quantity, " +
                    "    tblimportedaccessory.price, " +
                    "    tblaccessory.name AS accessname " +
                    "FROM " +
                    "    tblimportbill " +
                    "    LEFT JOIN tblmember AS distributor ON tblimportbill.distributor_id = distributor.id " +
                    "    LEFT JOIN tblmember AS employee ON tblimportbill.employee_id = employee.id " +
                    "    LEFT JOIN tblimportedaccessory ON tblimportedaccessory.importbill_id = tblimportbill.id " +
                    "    LEFT JOIN tblaccessory ON tblaccessory.id = tblimportedaccessory.accessory_id " +
                    "WHERE " +
                    "    tblimportbill.id = (" +
                    "        SELECT " +
                    "            tblimportbill.id " +
                    "        FROM " +
                    "            tblimportbill " +
                    "            LEFT JOIN tblimportedaccessory ON tblimportedaccessory.importbill_id = tblimportbill.id " +
                    "            LEFT JOIN tblaccessory ON tblimportedaccessory.accessory_id = tblaccessory.id " +
                    "            LEFT JOIN tblusedaccessory ON tblusedaccessory.accessory_id = tblaccessory.id " +
                    "        WHERE " +
                    "            tblusedaccessory.id = :usedId" +
                    "    )", nativeQuery = true)
    List<ResultSetQuery> importBillDetail(@Param("usedId")int usedId);
}
