package com.example.backendapp.service;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.exception.CustomException;
import com.example.backendapp.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerServiceRepositoryIntegrationTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void saveCustomerRecord(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("J");
        customer.setSurName("S");

        long id = customerService.save(customer);

        assertThat(id).isEqualTo(1L);
    }

    @Test
    public void findCustomerRecordById(){
        Customer customer = new Customer(1L, "John", "Smith");

        customerRepository.save(customer);

        Customer record = customerService.findById(customer.getId());
        assertThat(record).isEqualTo(customer);
    }

    @Test
    public void findAllCustomerRecords(){
        Customer c1 = new Customer(1L, "John", "Smith");
        customerRepository.save(c1);
        Customer c2 = new Customer(2L, "Mike", "Ashly");
        customerRepository.save(c2);

        List<Customer> expectedCustomers = Arrays.asList(c1, c2);

        List<Customer> customers = customerService.findAll();
        assertThat(customers).isEqualTo(expectedCustomers);
    }

    @Test
    public void throwExceptionWhenNoCustomerRecordsAreFound(){

        Exception exception = assertThrows(
                CustomException.class,
                ()-> { customerService.findAll();}
        );

        String expectedExceptionMessage = "No records are found";
        String actualExceptionMessage = exception.getMessage();

        assertTrue(actualExceptionMessage.contains(expectedExceptionMessage));
    }

    @Test
    public void updateCustomerRecord(){
        Customer customer = new Customer(1L, "John", "Smith");
        long id = customerRepository.save(customer).getId();

        customer.setFirstName("Sally");
        long record = customerService.update(id, customer);
        assertThat(record).isEqualTo(id);
    }

    @Test
    public void throwExceptionWhenNoCustomerRecordIsFoundToUpdate(){

        Customer customer = new Customer(1L, "John", "Smith");

        Exception exception = assertThrows(
                CustomException.class,
                ()-> { customerService.update(1L, customer);}
        );

        String expectedExceptionMessage = "Record with id: '1' not found";
        String actualExceptionMessage = exception.getMessage();

        assertTrue(actualExceptionMessage.contains(expectedExceptionMessage));
    }

    @Test
    public void deleteCustomerRecord(){
        Customer customer = new Customer(1L, "John", "Smith");

        long id = customerRepository.save(customer).getId();

        customerService.delete(id);

        Exception exception = assertThrows(
                CustomException.class,
                ()-> { customerService.findById(id);}
        );

        String expectedExceptionMessage = "Record with id: '1' not found";
        String actualExceptionMessage = exception.getMessage();

        assertTrue(actualExceptionMessage.contains(expectedExceptionMessage));
    }
}
