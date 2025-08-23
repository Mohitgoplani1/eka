package com.example.eka.controllers;

import com.example.eka.model.Customer;
import com.example.eka.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/customer")
public class AuthController {

    private final CustomerRepository customers;

    public AuthController(CustomerRepository customers) {
        this.customers = customers;
    }

    @PostMapping("/register")
    public String register(@RequestBody Customer customer) {
        if (customers.findByEmail(customer.getEmail()) != null) {
            return "Customer Already Exists!";
        }
        customers.save(customer);
        return "Customer registered Successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer loginRequest) {
        Customer existing = customers.findByEmail(loginRequest.getEmail());
        if (existing != null && existing.getPassword().equals(loginRequest.getPassword())) {
            return "Login Successful! Welcome " + existing.getName();
        }
        return "Login Failed!";
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomer() {
        return customers.findAll();
    }
}
