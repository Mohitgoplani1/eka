package com.example.eka.repository;

import com.example.eka.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    public List<Order> findByCustomerId(String customerId);
}
