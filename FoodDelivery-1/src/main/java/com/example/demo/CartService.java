package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

	private Cart cart;

    // Initialize the cart for the session
    public CartService() {
        this.cart = new Cart(1); // Using 1 as a default cartId
    }

    // Add item to the cart
    public void addItemToCart(CartItem item) {
        cart.addItem(item);
    }

    // Remove item from the cart by menuId
    public void removeItemFromCart(int menId) {
        cart.removeItem(menId);
    }

    // Get all items in the cart
    public Cart getCart() {
        return cart;
    }

    // Get total bill amount for the cart
    public double getTotalBillAmount() {
        return cart.getTotalBillAmount();
    }

    // Clear the cart after order placement
    public void clearCart() {
        cart.getItems().clear();
    }

    // Show all cart items
    public List<CartItem> showCartItems() {
        return cart.getItems();
    }
}