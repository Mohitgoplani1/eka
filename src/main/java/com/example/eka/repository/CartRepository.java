package com.example.eka.repository;

import com.example.eka.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {
    Cart findByCustomerId(String customerId);
}
