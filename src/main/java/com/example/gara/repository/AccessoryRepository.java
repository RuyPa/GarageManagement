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
    Accessory searchAccessoryById(@Param("id") int id);

    @Modifying
    @Query(value = "DELETE FROM gara.tblaccessory WHERE tblaccessory.id = :id", nativeQuery = true)
    void deleteAccessoryById(@Param("id") int id);
}
