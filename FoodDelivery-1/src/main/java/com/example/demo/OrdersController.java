package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
