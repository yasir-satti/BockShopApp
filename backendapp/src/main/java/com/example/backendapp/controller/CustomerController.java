package com.example.backendapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {

    @PostMapping
    public String createNewCustomer(){
        return "created new customer record";
    }

    @GetMapping
    public String getCustomerRecord(){
        return "got customer record";
    }
}
