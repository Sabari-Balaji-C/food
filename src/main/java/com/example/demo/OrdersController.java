package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping(value="/orders")
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    

    @PostMapping(value = "/placeOrder")
    public void placeOrder(@RequestBody Orders orders) throws Exception {
        ordersService.placeOrder(orders);
    }
    @GetMapping(value = "/showOrders")
    public List<Orders> showOrders() {
        return ordersService.showOrders();
    }
    
    @GetMapping(value = "/showOrdersByCustomer/{customerId}")
    public List<Orders> showOrdersByCustomer(@PathVariable int customerId) {
        return ordersService.findOrdersByCustomerId(customerId);
    }
    
   

    
}
