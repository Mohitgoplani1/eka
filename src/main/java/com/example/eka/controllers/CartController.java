package com.example.eka.controllers;

import com.example.eka.model.Cart;
import com.example.eka.model.Customer;
import com.example.eka.model.MenuItem;
import com.example.eka.repository.CartRepository;
import com.example.eka.repository.CustomerRepository;
import com.example.eka.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartRepository cartRepository;
    private final CustomerRepository customers;
    private final ItemRepository items;

    public CartController(CartRepository cartRepository, CustomerRepository customers, ItemRepository items) {
        this.cartRepository = cartRepository;
        this.customers = customers;
        this.items = items;
    }

    @GetMapping("/{customerId}")
    public Cart getCart(@PathVariable String customerId){
        return cartRepository.findByCustomerId(customerId);
    }
    @PostMapping("/add/{customerId}/{itemId}/{quantity}")
    public Cart addItem(@PathVariable String customerId,@PathVariable String itemId, @PathVariable int quantity){
        Customer customer=customers.findById(customerId).orElse(null);
        if(customer==null){
            throw new RuntimeException("Customer Not Found ");
        }
        MenuItem menuItem=items.findById(itemId).orElse(null);
        if(menuItem==null){
            throw new RuntimeException("Item not found ");
        }
        Cart cart=cartRepository.findByCustomerId(customerId);
        if(cart==null){
            cart=new Cart(null,customerId,new ArrayList<>(),0);
        }
        Cart.CartItem existing = null;
        for(Cart.CartItem ci:cart.getCartItems()){
            if(ci.getMenuItemId().equals(itemId)){
                existing=ci;
                break;
            }
        }
        if(existing!=null){
            existing.setQuantity(existing.getQuantity()+quantity);
            cart.setTotalPrice(cart.getTotalPrice()+ existing.getPrice()*quantity);
        }
        else{
            Cart.CartItem newItem=new Cart.CartItem(menuItem.getId(),menuItem.getName(),quantity,menuItem.getPrice());
            cart.getCartItems().add(newItem);
            cart.setTotalPrice(cart.getTotalPrice()+ newItem.getPrice()*quantity);
        }
        return cartRepository.save(cart);
    }
    @PostMapping("/delete/{customerId}/{itemId}/{quantity}")
    public Cart deleteItem(@PathVariable String customerId,@PathVariable String itemId,@PathVariable int quantity){
        Customer customer=customers.findById(customerId).orElse(null);
        if(customer==null){
            throw new RuntimeException("Customer not found ");
        }
        MenuItem menuItem=items.findById(itemId).orElse(null);
        if(menuItem==null){
            throw new RuntimeException("Item not Found ");
        }
        Cart cart=cartRepository.findByCustomerId(customerId);
        if(cart==null){
            throw new RuntimeException("Cart Not Found");
        }
        Cart.CartItem exists=null;
        for(Cart.CartItem ci:cart.getCartItems()){
            if(ci.getMenuItemId().equals(itemId)){
                exists=ci;
                break;
            }
        }
        if(exists==null){
            throw new RuntimeException("Item doesn't exists in cart");
        }
        else{
            exists.setQuantity(exists.getQuantity()-quantity);
            if(exists.getQuantity()==0){
                cart.getCartItems().remove(exists);
            }
            else{
                cart.setTotalPrice(cart.getTotalPrice()-quantity*exists.getPrice());
            }
        }
        return cartRepository.save(cart);
    }
    @PostMapping("/checkout/{customerId}")
    public void checkout(@PathVariable String customerId){
        cartRepository.delete(cartRepository.findByCustomerId(customerId));
    }
}
