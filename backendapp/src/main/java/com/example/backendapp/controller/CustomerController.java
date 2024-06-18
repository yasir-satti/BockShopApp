package com.example.backendapp.controller;

import com.example.backendapp.entity.Customer;
import com.example.backendapp.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.example.backendapp.swagger.SwaggerConstants.*;


@RestController
@RequestMapping("/api/1/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Tag(name = API_DOC_POST_GROUP_TAG, description = API_DOC_POST_GROUP_DESC)
    @Operation(summary = API_DOC_POST_OPS_SUM,
            description = API_DOC_POST_OPS_DESC)
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Void> createNewCustomer(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder){
        long id = customerService.save(customer);
        UriComponents uriComponents = uriComponentsBuilder.path("api/1/customer/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Tag(name = API_DOC_GET_GROUP_TAG, description = API_DOC_GET_GROUP_DESC)
    @Operation(summary = API_DOC_GET_BY_ID_OPS_SUM,
            description = API_DOC_GET_BY_ID_OPS_DESC)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerRecord(@PathVariable("id") long id){
        Customer record = customerService.findById(id);
        return ResponseEntity.ok(record);
    }

    @Tag(name = API_DOC_GET_GROUP_TAG, description = API_DOC_GET_GROUP_DESC)
    @Operation(summary = API_DOC_GET_ALL_OPS_SUM,
            description = API_DOC_GET_ALL_OPS_DESC)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomerRecord(){
        List<Customer> records = customerService.findAll();
        return ResponseEntity.ok(records);
    }

    @Tag(name = API_DOC_UPDATE_GROUP_TAG, description = API_DOC_UPDATE_GROUP_DESC)
    @Operation(summary = API_DOC_UPDATE_OPS_SUM,
            description = API_DOC_UPDATE_OPS_DESC)
    @PutMapping ("/{id}")
    public ResponseEntity<Long> updateCustomerRecord(@PathVariable("id") long id, @RequestBody Customer customer){
        Long retId = customerService.update(id, customer);
        return ResponseEntity.ok(retId);
    }

    @Tag(name = API_DOC_DELETE_GROUP_TAG, description = API_DOC_DELETE_GROUP_DESC)
    @Operation(summary = API_DOC_DELETE_OPS_SUM,
            description = API_DOC_DELETE_OPS_DESC)
    @DeleteMapping ("/{id}")
    public void deleteCustomerRecord(@PathVariable("id") long id){
        customerService.delete(id);
    }
}
