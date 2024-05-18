package com.example.backendapp.service;

import com.example.backendapp.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class Customers {

    public long create(Customer customer){
        return 1L;
    };

    public String getById(long id){
        return "record";
    };

    public String getAll(){
        return "record";
    };

    public String update(long id, Customer customer){
        return "updated record";
    };

    public String delete(long id){
        return "deleted record";
    };

}
