package com.example.gara.repository;

import com.example.gara.model.ImportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportBillRepository extends JpaRepository<ImportBill, Integer> {
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
    List<ResultSetQuery> getImportBill(@Param("usedId")int usedId);
}
