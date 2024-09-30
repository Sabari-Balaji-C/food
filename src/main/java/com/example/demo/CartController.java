package com.example.demo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /*@GetMapping("/showCart")
    public List<Cart> showItems() {
		return cartService.showItems();
	}*/
    @PostMapping("/add")
    public void addItem(@RequestBody Cart item) {
        cartService.addItem(item);
    }

    @DeleteMapping("/remove/{cusId}/{menId}")
    public void removeItem(@PathVariable int cusId, @PathVariable int menId) {
        cartService.removeItem(cusId, menId);
    }

    /*@GetMapping("/items/{cusId}")
    public List<Cart> getCartItems(@PathVariable int cusId) {
        return cartService.getCartItems(cusId);
    }*/
    
    @PutMapping("/updateCart")
    public void updateCart(@RequestBody Cart cart) {
        cartService.updateCartItem(cart);
    }


    @DeleteMapping("/clearAll")
    public void clearCart2() {
        cartService.clearAll();
    }
    
    @GetMapping("/totalCost/{cusId}")
    public double getTotalCost(@PathVariable int cusId) {
        return cartService.calculateTotalCost(cusId);
    }
    
    @GetMapping("/showCart/{cusId}") // Updated to accept customer ID
    public ResponseEntity<List<Cart>> getCartItems(@PathVariable int cusId) {
        List<Cart> cartItems = cartService.getCartItems(cusId);
        if (cartItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
    
}