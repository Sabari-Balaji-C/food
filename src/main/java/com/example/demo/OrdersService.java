package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import jakarta.transaction.Transactional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepo;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private WalletService walletService;
    
    @Autowired
    private CartRepository cartRepo;
    
    @Autowired
    private CustomerRepository customerRepo; 
     
    /*@Autowired
   private JavaMailSender mailSender;*/

    @Transactional
    public String placeOrder(Orders orders) {
    	String result="";

        List<Cart> cartItems = cartRepo.findByCusId(orders.getCusId());

        double walletAmount = walletService.getWalletAmount(orders.getCusId(), orders.getWalSource());
        double totalOrderAmount = 0;

        for (Cart cartItem : cartItems) {
            double totalBillAmount = calculateTotalBillAmount(cartItem.getMenId(), cartItem.getQuantity());

            if (walletAmount >= totalBillAmount) {
                Orders newOrder = new Orders();
                newOrder.setCusId(orders.getCusId());
                newOrder.setVenId(orders.getVenId());
                newOrder.setMenId(cartItem.getMenId());
                newOrder.setOrdQuantity(cartItem.getQuantity());
                newOrder.setOrdBillAmount(totalBillAmount);
                newOrder.setOrdStatus("ACCEPTED");
                newOrder.setWalSource(orders.getWalSource());
                newOrder.setOrdDate(Date.valueOf(LocalDate.now())); // Set current date
                newOrder.setOrdComments(orders.getOrdComments());
                
                ordersRepo.save(newOrder);
                
                walletAmount -= totalBillAmount;
                walletService.updateWalletAmount(orders.getCusId(), orders.getWalSource(), walletAmount);

                cartRepo.deleteByCusIdAndMenId(orders.getCusId(), cartItem.getMenId());
                
                totalOrderAmount += totalBillAmount;
              result= "Order placed successfully!";
              /*sendEmailNotification(orders.getCusId(), newOrder);*/


            } else {
                Orders deniedOrder = new Orders();
                deniedOrder.setCusId(orders.getCusId());
                deniedOrder.setVenId(orders.getVenId());
                deniedOrder.setMenId(cartItem.getMenId());
                deniedOrder.setOrdQuantity(cartItem.getQuantity());
                deniedOrder.setOrdBillAmount(totalBillAmount);
                deniedOrder.setOrdStatus("DENIED");
                deniedOrder.setWalSource(orders.getWalSource());
                deniedOrder.setOrdDate(Date.valueOf(LocalDate.now())); // Set current date
                deniedOrder.setOrdComments(orders.getOrdComments());
                
                ordersRepo.save(deniedOrder);
                cartRepo.deleteByCusIdAndMenId(orders.getCusId(), cartItem.getMenId());
             result= "Insufficient wallet balance. Order denied.";
            }
        }
		return result;

    }

    public double calculateTotalBillAmount(int menuId, int quantity) {
        String sql = "SELECT MEN_PRICE FROM menu WHERE MEN_ID = ?";
        Double price = jdbcTemplate.queryForObject(sql, new Object[]{menuId}, Double.class);
        return price * quantity;
    }
    
    public List<Orders> findOrdersByCustomerId(int customerId) {
        return ordersRepo.findByCusId(customerId);
    }

    public List<Orders> showOrders() {
        return ordersRepo.findAll();
    }
    /*private void sendEmailNotification(int customerId, Orders order) {
        // Retrieve the customer from the database using customerId
        Customer customer = customerRepo.findByCusId(customerId);
        
        if (customer != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            
            // Set the recipient's email address
            message.setTo(customer.getCusEmail()); // Use the customer's email
            
            // Set the email subject
            message.setSubject("Order Confirmation");
            
            // Set the email body text
            message.setText("Dear Customer,\n\nYour order has been placed successfully!\n\n" +
                    "Order Details:\n" +
                    "Order ID: " + order.getOrdId() + "\n" +
                    "Vendor ID: " + order.getVenId() + "\n" +
                    "Menu Item ID: " + order.getMenId() + "\n" +
                    "Quantity: " + order.getOrdQuantity() + "\n" +
                    "Total Amount: " + order.getOrdBillAmount() + "\n" +
                    "Status: " + order.getOrdStatus() + "\n\n" +
                    "Thank you for your order!\n" +
                    "Best regards,\n" +
                    "Your Food Delivery Service");

            // Send the email
            mailSender.send(message);
        } else {
            // Handle the case when the customer is not found
            System.out.println("Customer not found for ID: " + customerId);
        }
    }*/

}   