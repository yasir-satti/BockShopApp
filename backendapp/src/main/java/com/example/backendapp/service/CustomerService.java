package com.example.backendapp.service;

import com.example.backendapp.exception.CustomException;
import com.example.backendapp.entity.Customer;
import com.example.backendapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public long save(Customer customer){
        return customerRepository.save(customer).getId();
    }

    public Customer findById(long id){
        Optional<Customer> record = customerRepository.findById(id);
        if(record.isEmpty()) {
            throw new CustomException(String.format("Record with id: '%s' not found", id));
        }
        return record.get();
    }

    public List<Customer> findAll(){
        List<Customer> record = customerRepository.findAll();
        if(record.isEmpty()) {
            throw new CustomException("No records are found");
        }
        return record;
    }

    public long update(long id, Customer modifiedCustomer){
        Optional<Customer> recordToUpdate = Optional.ofNullable(this.findById(id));
        Customer newRecord = recordToUpdate.get();
        newRecord.setFirstName(modifiedCustomer.getFirstName());
        return customerRepository.save(newRecord).getId();
    }

    public void delete(long id){
        Optional<Customer> recordToDelete = Optional.ofNullable(this.findById(id));
        Customer record = recordToDelete.get();
        customerRepository.delete(record);
    }

}
