package com.example.backendapp.repository;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("application-test.properties")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void h2DbCreatesInitialRecords(){
        List<Customer> customers = customerService.findAll();
        assertEquals(customers.size(), 2);
    }
}
