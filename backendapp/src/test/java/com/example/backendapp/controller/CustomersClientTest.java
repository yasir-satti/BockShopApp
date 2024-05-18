package com.example.backendapp.controller;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.service.Customers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomersClient.class)
public class CustomersClientTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Customers customers;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<Customer> argumentCaptor;

    @Test
    public void createNewCustomerRecord() throws Exception {

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setSurName("Smith");

        when(customers.create(argumentCaptor.capture())).thenReturn(1L);

        this.mockMvc.perform(
                post("http://localhost/api/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer))
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk())
                .andExpect(content().string("created new customer record"));
    }

    @Test
    public void getCustomerRecord() throws Exception {

        when(customers.getById(anyInt())).thenReturn("got customer record");

        this.mockMvc.perform(
                        get("http://localhost/api/1")
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk())
                .andExpect(content().string("got customer record"));;
    }
}
