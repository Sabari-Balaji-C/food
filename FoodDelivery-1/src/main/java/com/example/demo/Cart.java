package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int cartId;
    private List<CartItem> items;
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    public Cart(int cartId) {
        this.cartId = cartId;
        this.items = new ArrayList<>();
    }

    // Add an item to the cart
    public void addItem(CartItem item) {
        items.add(item);
    }

    // Get total bill amount from all items in the cart
    public double getTotalBillAmount() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getOrdBillAmount();
        }
        return total;
    }

    // Remove an item from the cart by item ID
    public void removeItem(int menId) {
        items.removeIf(item -> item.getMenId() == menId);
    }
}
