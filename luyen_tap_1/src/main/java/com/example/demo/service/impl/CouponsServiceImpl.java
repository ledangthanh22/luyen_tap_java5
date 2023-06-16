package com.example.demo.service.impl;

import com.example.demo.entity.Coupons;
import com.example.demo.repository.CouponsRepository;
import com.example.demo.service.CouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class CouponsServiceImpl implements CouponsService {

    @Autowired
    private CouponsRepository couponsRepository;

    @Override
    public Page<Coupons> findPagination(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return couponsRepository.findAll(pageable);
    }

    @Override
    public Coupons getOne(String id) {
        Optional<Coupons> result = couponsRepository.findById(id);
        Coupons coupons = null;
        if (result.isPresent()) {
            coupons = result.get();
        } else {
            throw new RuntimeException("Coupons code could not be found: " + id);
        }
        return coupons;
    }

    @Override
    public void save(Coupons coupons) {
        couponsRepository.save(coupons);
    }

    @Override
    public void delete(String id) {
        couponsRepository.deleteById(id);
    }


    @Override
    public Page<Coupons> search(Date startDay, Date endDay, String code, Pageable pageable) {
        return couponsRepository.search(startDay,endDay,code,pageable);
    }

}
