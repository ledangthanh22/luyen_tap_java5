package com.example.demo.service;

import com.example.demo.entity.Coupons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;

public interface CouponsService {

    Page<Coupons> findPagination(Integer pageNo, Integer pageSize);

    Coupons getOne(String id);

    void save(Coupons coupons);

    void delete(String id);

    Page<Coupons> search(Date startDay, Date endDay, String code, Pageable pageable);

}
