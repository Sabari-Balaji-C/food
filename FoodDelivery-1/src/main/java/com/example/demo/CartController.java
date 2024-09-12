package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
    private CartService cartService;

    @Autowired
    private OrdersService ordersService;

    // Add an item to the cart
    @PostMapping("/addItem")
    public String addItemToCart(@RequestBody CartItem item) {
        cartService.addItemToCart(item);
        return "Item added to cart.";
    }

    // Remove an item from the cart
    @DeleteMapping("/removeItem/{menId}")
    public String removeItemFromCart(@PathVariable int menId) {
        cartService.removeItemFromCart(menId);
        return "Item removed from cart.";
    }

    // View cart contents
    @GetMapping("/view")
    public Cart viewCart() {
        return cartService.getCart();
    }

    // View all cart items
    @GetMapping("/showCartItems")
    public List<CartItem> showCartItems() {
        return cartService.showCartItems();
    }

    // Place the order using the cart items
    @PostMapping("/placeOrder/{cusId}/{walSource}")
    public String placeOrder(@PathVariable int cusId, @PathVariable String walSource) {
        Cart cart = cartService.getCart();
        double totalBill = cartService.getTotalBillAmount();

        Orders order = new Orders();
        order.setCusId(cusId);
        order.setWalSource(walSource);
        order.setOrdQuantity(cart.getItems().size()); // Example for quantity (could differ)
        order.setOrdBillAmount(totalBill);

        // Place order using OrdersService
        String result = ordersService.placeCartOrder(cusId, walSource, cart);
        if (result.equals("Order placed successfully!")) {
            cartService.clearCart(); // Clear cart after successful order
        }

        return result;
    }
}