package com.example.backendapp.controller;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createNewCustomerRecord() throws Exception {

        Customer customer = new Customer(1L, "John", "Smith");

        when(customerService.save(customer)).thenReturn(1L);

        this.mockMvc.perform(
                post("http://localhost/api/1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer))
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/1/customer/1"));
    }

    @Test
    public void getCustomerRecordById() throws Exception {

        Customer customer = new Customer(3L, "John", "Smith");

        when(customerService.findById(anyInt())).thenReturn(customer);

        this.mockMvc.perform(
                        get("/api/1/customer/3")
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    public void getAllCustomerRecords() throws Exception {

        Customer c1 = new Customer(1L, "John", "Smith");
        Customer c2 = new Customer(2L, "Mike", "Ashly");
        List<Customer> customersList = Arrays.asList(c1, c2);

        when(customerService.findAll()).thenReturn(customersList);

        this.mockMvc.perform(
                        get("/api/1/customer")
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    public void updateCustomerRecord() throws Exception {

        Customer customer = new Customer(1L, "John", "Smith");

        when(customerService.update(1L, customer)).thenReturn(1L);

        this.mockMvc.perform(
                        put("/api/1/customer/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customer))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("1"));
    }
}
