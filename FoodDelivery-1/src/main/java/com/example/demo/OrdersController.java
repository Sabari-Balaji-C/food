package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping(value = "/placeOrder")
    public String placeOrder(@RequestBody Orders orders) {
        return ordersService.placeOrder(orders);
    }

    @GetMapping(value = "/showOrders")
    public List<Orders> showOrders() {
        return ordersService.showOrders();
    }
    
    @PostMapping(value = "/placeCartOrder/{cusId}/{walSource}")
    public String placeCartOrder(@PathVariable int cusId, @PathVariable String walSource, @RequestBody Cart cart) {
        return ordersService.placeCartOrder(cusId, walSource, cart);
    }
    
    @GetMapping(value = "/showCartOrders/{cusId}")
    public List<Orders> showCartOrders(@PathVariable int cusId) {
        return ordersService.showCartOrders(cusId);
    }
    
    @PutMapping(value = "/dispatchOrder/{ordId}")
    public String dispatchOrder(@PathVariable int ordId) {
        return ordersService.dispatchOrder(ordId);
    }
    @PutMapping(value = "/setEstimatedDeliveryTime/{ordId}/{estimatedTime}")
    public String setEstimatedDeliveryTime(@PathVariable int ordId, @PathVariable String estimatedTime) {
        return ordersService.setEstimatedDeliveryTime(ordId, estimatedTime);
    }
}
