package com.example.gara.repository;


import com.example.gara.model.Distributor;
import com.example.gara.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributorRepsitory extends JpaRepository<Member, Integer> {
    @Query(value = "select * from tbldistributor left join tblmember on tbldistributor.member_id = tblmember.id where tblmember.username like %:key%", nativeQuery = true)
    List<ResultSetQuery> getDistributorByKey(@Param("key")String key);

    @Query(value = "select * from tbldistributor left join tblmember on tbldistributor.member_id = tblmember.id where tblmember.id = :id", nativeQuery = true)
    ResultSetQuery getDistributorById(@Param("id")int id);

}
