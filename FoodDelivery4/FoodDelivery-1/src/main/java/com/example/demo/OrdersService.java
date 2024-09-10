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
      
        double walletAmount = walletService.getWalletAmount(orders.getCusId(), orders.getWalSource());
        double totalBillAmount = calculateTotalBillAmount(orders.getMenId(), orders.getOrdQuantity());
        if (walletAmount >= totalBillAmount) {
            orders.setOrdBillAmount(totalBillAmount);
            orders.setOrdStatus("ACCEPTED");
            ordersRepo.save(orders); 
            walletService.updateWalletAmount(orders.getCusId(), orders.getWalSource(), walletAmount - totalBillAmount);
            return "Order placed successfully!";
        } else {
            orders.setOrdStatus("DENIED");
            ordersRepo.save(orders);  
            return "Insufficient wallet balance. Order denied.";
        }
    }
    public double calculateTotalBillAmount(int menuId, int quantity) {
        String sql = "SELECT MEN_PRICE FROM menu WHERE MEN_ID = ?";
        Double price = jdbcTemplate.queryForObject(sql, new Object[]{menuId}, Double.class);
        return price * quantity;
    }
    
    public List<Orders> showOrders() {
        return ordersRepo.findAll();
    }
}
   