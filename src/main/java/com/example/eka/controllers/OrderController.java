package com.example.eka.controllers;

import com.example.eka.model.Cart;
import com.example.eka.model.MenuItem;
import com.example.eka.model.Order;
import com.example.eka.repository.CartRepository;
import com.example.eka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrderRepository orders;
    private final CartRepository carts;

    public OrderController(OrderRepository orders, CartRepository carts) {
        this.orders = orders;
        this.carts = carts;
    }

    @RequestMapping("/cancel/{orderId}")
    public void cancelOrder(@PathVariable String orderId){
        Order order=orders.findById(orderId).orElse(null);
        if(order!=null){
            order.setOrderStatus("Cancelled");
            orders.save(order);
        }
        else throw new RuntimeException("Order Not found ");
    }

    @RequestMapping("/placeOrder")
    public Order placeOrder(@RequestBody Order order){
        Cart cart = carts.findById(order.getCartId()).orElseThrow(() -> new RuntimeException("Cart Not Found"));

        int total = cart.getCartItems().stream()
                .mapToInt(i -> i.getPrice() * i.getQuantity())
                .sum();

        if(order.getTotalPrice() != total) {
            throw new RuntimeException("Error in Cart Value");
        }

        order.setCreatedAt(new Date());
        return orders.save(order);
    }


    @RequestMapping("/all")
    public List<Order> getAllOrders(){
        return orders.findAll();
    }

    @RequestMapping("/{customerId}")
    public List<Order> getByCustomerId(@PathVariable String customerId){
        return orders.findByCustomerId(customerId);
    }

    @RequestMapping("/{customerId}/{orderId}")
    public Order getById(@PathVariable String customerId,@PathVariable String orderId){
        return orders.findById(orderId).orElse(null);
    }
}
