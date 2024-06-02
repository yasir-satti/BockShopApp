package com.example.backendapp.service;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("application-test.properties")
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup(){}

    @Test
    public void saveCustomerRecord(){

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setSurName("Smith");

        when(customerRepository.save(customer)).thenReturn(customer);

        long id = customerService.save(customer);

        assertThat(id).isEqualTo(1L);
    }

    @Test
    public void getCustomerRecordById(){

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setSurName("Smith");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        Optional<Customer> record = Optional.ofNullable(customerService.findById(1L));

        assertThat(record).isEqualTo(Optional.of(customer));
    }

    @Test
    public void findAllCustomerRecords(){

        Customer c1 = new Customer(1L, "John", "Smith");
        Customer c2 = new Customer(2L, "Mike", "Ashly");
        List<Customer> customersList = Arrays.asList(c1, c2);

        when(customerRepository.findAll()).thenReturn(customersList);

        List<Customer> record = customerService.findAll();

        assertThat(record).isEqualTo(customersList);
    }

    @Test
    public void updateCustomerRecord(){

        Customer recordToUpdate = new Customer(1L, "John", "Smith");
        Customer updatedRecord = new Customer(1L, "Mike", "Smith");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(recordToUpdate));
        when(customerRepository.save(updatedRecord)).thenReturn(updatedRecord);

        long id = customerService.update(1L, updatedRecord);

        assertThat(id).isEqualTo(1L);
    }

//    @Test
//    public void deleteCustomerRecord(){
//
//        Customer customer = new Customer(1L, "John", "Smith");
//
//        when(customerRepository.delete(customer)).thenReturn(customer);
//
//        customerService.delete(1L);
//    }
}
