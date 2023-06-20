package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT o FROM Customer o WHERE (:keyword IS NULL OR ( o.name = :keyword OR o.phoneNumber = :keyword)) AND o.customerClass.code = :code ")
    Page<Customer> search(@Param("keyword") String keyword,@Param("code") String nameCustomerClass, Pageable pageable);
}
