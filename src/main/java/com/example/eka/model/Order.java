package com.example.eka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private String customerId;
    private String cartId;
    private String orderStatus;
    private int totalPrice;

    private Date createdAt;


    public Order() {
    }

    public Order(String customerId, String cartId, String orderStatus, int totalPrice, Date createdAt) {
        this.customerId = customerId;
        this.cartId = cartId;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
    public String getCartId() {
        return cartId;
    }

    public void setCart(String cart) {
        this.cartId = cart;
    }

    public String getId() {
        return id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
