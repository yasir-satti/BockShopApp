package com.example.backendapp.service;

import com.example.backendapp.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class CustomersTest {

    Customers mockCustomers;

    @BeforeEach
    public void setup(){
        mockCustomers = Mockito.mock(Customers.class);
    }

    @Test
    public void whenCreatingNewCustomer_getRecordId(){
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setSurName("Smith");
        when(mockCustomers.create(any())).thenReturn(1L);
        long id = mockCustomers.create(customer);
        assertThat(id).isEqualTo(1L);
    }

    @Test
    public void whenCustomerRecordRead_getRecordData(){
        when(mockCustomers.getById(anyInt())).thenReturn("record");
        String record = mockCustomers.getById(1L);
        assertThat(record).isEqualTo("record");
    }

    @Test
    public void whenAllCustomersRecordsRead_getRecordsData(){
        when(mockCustomers.getAll()).thenReturn("record");
        String record = mockCustomers.getAll();
        assertThat(record).isEqualTo("record");
    }

    @Test
    public void whenUserRecordUpdated_getRecordData(){
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setSurName("Smith");
        when(mockCustomers.update(anyInt(), any())).thenReturn("updated record");
        String record = mockCustomers.update(1L, customer);
        assertThat(record).isEqualTo("updated record");
    }

    @Test
    public void whenUserRecordDeleted_getRecordData(){
        when(mockCustomers.delete(anyInt())).thenReturn("deleted record");
        String record = mockCustomers.delete(1L);
        assertThat(record).isEqualTo("deleted record");
    }
}
