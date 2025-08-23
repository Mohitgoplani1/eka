package com.example.eka.repository;

import com.example.eka.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByEmail(String email);
}
