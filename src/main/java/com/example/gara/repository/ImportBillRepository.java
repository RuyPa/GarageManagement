package com.example.gara.repository;

import com.example.gara.model.Accessory;
import com.example.gara.model.ImportBill;
import com.example.gara.model.ImportedAccessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Modifying
    @Query(value = "INSERT INTO tblimportedaccessory (id, date, quantity, price, accessory_id, importBill_id) " + "VALUES (:#{#importedAccessory.id}, :#{#importedAccessory.date}, :#{#importedAccessory.quantity}, :#{#importedAccessory.price}, :#{#importedAccessory.accessory.id}, :#{#importbill_id})", nativeQuery = true)
    void insertImportedAccessory(@Param("importedAccessory")ImportedAccessory importedAccessory,
                                @Param("importbill_id")int importbill_id);

    @Modifying
    @Query(value = "INSERT INTO tblimportbill (id, date, discount, employee_id, distributor_id) " + "VALUES (:#{#importbill.id}, :#{#importbill.date}, :#{#importbill.discount}, :#{#importbill.employee.id}, :#{#importbill.distributor.id})", nativeQuery = true)
    void createImportBill(@Param("importbill")ImportBill importbill);

}
