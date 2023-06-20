package com.example.demo.service.impl;

import com.example.demo.entity.CustomerClass;
import com.example.demo.repository.CustomerClassRepository;
import com.example.demo.service.CustomerClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerClassServiceImpl implements CustomerClassService {

    @Autowired
    private CustomerClassRepository customerClassRepository;
    @Override
    public List<CustomerClass> getAll() {
        return customerClassRepository.findAll();
    }
}
