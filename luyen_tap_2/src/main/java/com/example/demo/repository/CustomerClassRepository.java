package com.example.demo.repository;

import com.example.demo.entity.CustomerClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerClassRepository extends JpaRepository<CustomerClass,Integer> {

}
