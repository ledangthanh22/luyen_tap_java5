package com.example.demo.service;

import com.example.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CustomerService {

    Page<Customer> findPagination(Integer pageNo, Integer pageSize);

    void save(Customer customer);

    void delete(Long code);


    Page<Customer> search(String keyword, String nameCustomerClass, Pageable pageable);

}
