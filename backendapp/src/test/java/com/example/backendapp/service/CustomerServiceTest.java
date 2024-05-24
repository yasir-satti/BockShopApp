package com.example.backendapp.service;

import com.example.backendapp.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @BeforeEach
    public void setup(){}

    @Test
    public void whenCreatingNewCustomer_getRecordId(){
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setSurName("Smith");
        long id = customerService.create(customer);
        assertThat(id).isEqualTo(1L);
    }

    @Test
    public void whenCustomerRecordRead_getRecordData(){
        String record = customerService.getById(1L);
        assertThat(record).isEqualTo("record");
    }

    @Test
    public void whenAllCustomersRecordsRead_getRecordsData(){
        String record = customerService.getAll();
        assertThat(record).isEqualTo("record");
    }

    @Test
    public void whenUserRecordUpdated_getRecordData(){
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setSurName("Smith");
        String record = customerService.update(1L, customer);
        assertThat(record).isEqualTo("updated record");
    }

    @Test
    public void whenUserRecordDeleted_getRecordData(){
        String record = customerService.delete(1L);
        assertThat(record).isEqualTo("deleted record");
    }
}
