package com.example.demo.repository;

import com.example.demo.entity.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons,String> {

    @Query("SELECT c FROM Coupons c WHERE  (:startDay IS NULL OR c.startDay >= :startDay)  AND (:endDay IS NULL OR c.startDay <= :endDay) AND c.customer.id = :code")
    Page<Coupons> search(@Param("startDay") Date startDay,@Param("endDay") Date endDay,@Param("code") String code, Pageable pageable);

}
