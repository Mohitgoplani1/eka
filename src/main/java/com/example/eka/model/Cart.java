package com.example.eka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private String customerId;
    private List<CartItem> cartItems;
    private int totalPrice;

    public Cart(String id, String customerId, List<CartItem> cartItems, int totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
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

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static class CartItem{
        private String menuItemId;
        private String name;
        private int quantity;
        private int price;

        public CartItem(String menuItemId, String name, int quantity, int price) {
            this.menuItemId = menuItemId;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getMenuItemId() {
            return menuItemId;
        }

        public void setMenuItemId(String menuItemId) {
            this.menuItemId = menuItemId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
