package com.example.gara.repository;


import com.example.gara.model.Accessory;
import com.example.gara.model.Distributor;
import com.example.gara.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributorRepsitory extends JpaRepository<Member, Integer> {
    @Query(value = "select * from tbldistributor left join tblmember on tbldistributor.member_id = tblmember.id where tblmember.name like %:key%", nativeQuery = true)
    List<ResultSetQuery> getDistributorByKey(@Param("key")String key);

    @Query(value = "select * from tbldistributor left join tblmember on tbldistributor.member_id = tblmember.id where tblmember.id = :id", nativeQuery = true)
    ResultSetQuery getDistributorById(@Param("id")int id);

    @Query(value = "select * from tblmember where tblmember.username = :username", nativeQuery = true)
    ResultSetQuery getAccount(@Param("username") String username);

    @Modifying
    @Query(value = "INSERT INTO tblmember (id, username, password, name, dob, email, phonenumber, address) " + "VALUES (:#{#member.id}, :#{#member.username}, :#{#member.password}, :#{#member.name},:#{#member.dob}, :#{#member.email}, :#{#member.phonenumber}, :#{#member.address})", nativeQuery = true)
    void insertMember(@Param("member") Member member);

    @Modifying()
    @Query(value = "insert into tbldistributor (member_id)" + "VALUES (:#{#member.id})", nativeQuery = true)
    void insertDistributor(@Param("member")Member member);
}
