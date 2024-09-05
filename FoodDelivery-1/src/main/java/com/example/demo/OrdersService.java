package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepo;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private WalletService walletService;

    public String placeOrder(Orders orders) {
        // Step 1: Fetch the customer wallet balance for the given WAL_SOURCE
        double walletAmount = walletService.getWalletAmount(orders.getCusId(), orders.getWalSource());

        // Step 2: Calculate the total order bill amount
        double totalBillAmount = calculateTotalBillAmount(orders.getMenId(), orders.getOrdQuantity());

        // Step 3: Check if the wallet balance is sufficient
        if (walletAmount >= totalBillAmount) {
            // Step 4: Place the order with ACCEPTED status
            orders.setOrdBillAmount(totalBillAmount);
            orders.setOrdStatus("ACCEPTED");
            ordersRepo.save(orders);  // Insert order in 'orders' table

            // Step 5: Update the wallet balance
            walletService.updateWalletAmount(orders.getCusId(), orders.getWalSource(), walletAmount - totalBillAmount);

            return "Order placed successfully!";
        } else {
            // If balance is insufficient, place the order with DENIED status
            orders.setOrdStatus("DENIED");
            ordersRepo.save(orders);  // Insert order in 'orders' table with DENIED status

            return "Insufficient wallet balance. Order denied.";
        }
    }

    // Calculate total bill based on menu item price and quantity
    private double calculateTotalBillAmount(int menuId, int quantity) {
        String sql = "SELECT MEN_PRICE FROM menu WHERE MEN_ID = ?";
        Double price = jdbcTemplate.queryForObject(sql, new Object[]{menuId}, Double.class);
        return price * quantity;
    }
    
    public List<Orders> showOrders() {
        return ordersRepo.findAll();
    }
}
   