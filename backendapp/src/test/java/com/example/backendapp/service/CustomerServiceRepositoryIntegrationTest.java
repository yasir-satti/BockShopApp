package com.example.backendapp.service;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.jdbc.JdbcTestUtils;

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
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup(){}

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

        long id = customerService.save(customer);

        Customer record = customerService.findById(id);
        assertThat(record).isEqualTo(customer);
    }

    @Test
    public void findAllCustomerRecords(){
        Customer c1 = new Customer(1L, "John", "Smith");
        Customer c2 = new Customer(2L, "Mike", "Ashly");
        List<Customer> expectedCustomers = Arrays.asList(c1, c2);

        List<Customer> customers = customerService.findAll();
        assertThat(customers).isEqualTo(expectedCustomers);
    }

    @Test
    public void throwExceptionWhenNoCustomerRecordsAreFound(){

        JdbcTestUtils.deleteFromTables(jdbcTemplate, "customers");

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
        long id = customerService.save(customer);

        customer.setFirstName("Sally");
        long record = customerService.update(id, customer);
        assertThat(record).isEqualTo(id);
    }

    @Test
    public void deleteCustomerRecord(){
        Customer customer = new Customer(1L, "John", "Smith");

        long id = customerService.save(customer);

        customerService.delete(customer);

        Exception exception = assertThrows(
                CustomException.class,
                ()-> { customerService.findById(id);}
        );

        String expectedExceptionMessage = "Record with id: '1' not found";
        String actualExceptionMessage = exception.getMessage();

        assertTrue(actualExceptionMessage.contains(expectedExceptionMessage));
    }
}
