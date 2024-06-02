package com.example.backendapp.controller;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/1/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createNewCustomer(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder){
        long id = customerService.save(customer);
        UriComponents uriComponents = uriComponentsBuilder.path("api/1/customer/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerRecord(@PathVariable("id") long id){
        Customer record = customerService.findById(id);
        return ResponseEntity.ok(record);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomerRecord(){
        List<Customer> records = customerService.findAll();
        return ResponseEntity.ok(records);
    }

    @PutMapping ("/{id}")
    public long updateCustomerRecord(@PathVariable("id") long id, Customer customer){
        return customerService.update(id, customer);
    }

    @DeleteMapping ("/{id}")
    public void deleteCustomerRecord(@PathVariable("id") long id){
        customerService.delete(id);
    }
}
