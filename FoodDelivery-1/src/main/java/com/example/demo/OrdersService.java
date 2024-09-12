package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepo;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private WalletService walletService;
    
    @Autowired
    private JavaMailSender mailSender;

    public String placeOrder(Orders orders) {
        double walletAmount = walletService.getWalletAmount(orders.getCusId(), orders.getWalSource());
        double totalBillAmount = calculateTotalBillAmount(orders.getMenId(), orders.getOrdQuantity());
        if (walletAmount >= totalBillAmount) {
            orders.setOrdBillAmount(totalBillAmount);
            orders.setOrdStatus("ACCEPTED");
            ordersRepo.save(orders); 

            walletService.updateWalletAmount(orders.getCusId(), orders.getWalSource(), walletAmount - totalBillAmount);

            // Send order confirmation email
            sendOrderConfirmationEmail(orders.getCusId(), totalBillAmount);

            return "Order placed successfully!";
        } else {
            orders.setOrdStatus("DENIED");
            ordersRepo.save(orders);  
            return "Insufficient wallet balance. Order denied.";
        }
    }
    
    private void sendOrderConfirmationEmail(int cusId, double totalBillAmount) {
        String sql = "SELECT CUS_NAME, CUS_EMAIL FROM customer WHERE CUS_ID = ?";
        Map<String, Object> customer = jdbcTemplate.queryForMap(sql, cusId);

        String customerName = (String) customer.get("CUS_NAME");
        String customerEmail = (String) customer.get("CUS_EMAIL");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customerEmail);
        message.setSubject("Order Confirmation");

        String body = "Dear " + customerName + ",\n\n" +
                      "Thank you for placing your order with HotByte.\n" +
                      "Total Bill Amount: " + totalBillAmount + "\n\n" +
                      "Your order has been successfully placed.\n\n" +
                      "Best regards,\n" +
                      "The HotByte Team";
        message.setText(body);

        mailSender.send(message);
        System.out.println("Order confirmation email sent to: " + customerEmail);
    }

    public double calculateTotalBillAmount(int menuId, int quantity) {
        String sql = "SELECT MEN_PRICE FROM menu WHERE MEN_ID = ?";
        Double price = jdbcTemplate.queryForObject(sql, new Object[]{menuId}, Double.class);
        return price * quantity;
    }
    
    public List<Orders> showOrders() {
        return ordersRepo.findAll();
    }
    
    public String placeCartOrder(int cusId, String walSource, Cart cart) {
        double walletAmount = walletService.getWalletAmount(cusId, walSource);
        double totalCartAmount = cart.getTotalBillAmount();
        if (walletAmount >= totalCartAmount) {
            for (CartItem item : cart.getItems()) {
                Orders order = new Orders();
                order.setCusId(cusId);
                order.setWalSource(walSource);
                order.setMenId(item.getMenId());
                order.setOrdQuantity(item.getOrdQuantity());
                order.setOrdBillAmount(item.getOrdBillAmount());
                order.setOrdStatus("ACCEPTED");
                order.setOrdDate(new java.sql.Date(System.currentTimeMillis())); 

                ordersRepo.save(order);
            }

            walletService.updateWalletAmount(cusId, walSource, walletAmount - totalCartAmount);

            // Send cart order confirmation email
            sendOrderConfirmationEmail(cusId, totalCartAmount);

            return "Cart order placed successfully!";
        } else {
            return "Insufficient wallet balance for the cart order.";
        }
    }

    public List<Orders> showCartOrders(int cusId) {
        String sql = "SELECT * FROM orders WHERE CUS_ID = ? AND ORD_STATUS = 'ACCEPTED'";
        return jdbcTemplate.query(sql, new Object[]{cusId}, new RowMapper<Orders>() {
            @Override
            public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
                Orders order = new Orders();
                order.setOrdId(rs.getInt("ORD_ID"));
                order.setCusId(rs.getInt("CUS_ID"));
                order.setMenId(rs.getInt("MEN_ID"));
                order.setOrdQuantity(rs.getInt("ORD_QUANTITY"));
                order.setOrdBillAmount(rs.getDouble("ORD_BILLAMOUNT"));
                order.setOrdStatus(rs.getString("ORD_STATUS"));
                order.setOrdDate(rs.getDate("ORD_DATE"));
                return order;
            }
        });
    }

    // Method to dispatch an order
    public String dispatchOrder(int ordId) {
        Orders order = ordersRepo.findById(ordId).orElse(null);
        if (order == null) {
            return "Order not found.";
        }

        order.setOrdStatus("DISPATCHED");
        ordersRepo.save(order);

        sendDispatchNotificationEmail(order.getCusId(), ordId);

        return "Order dispatched successfully!";
    }

    private void sendDispatchNotificationEmail(int cusId, int ordId) {
        String sql = "SELECT CUS_NAME, CUS_EMAIL FROM customer WHERE CUS_ID = ?";
        Map<String, Object> customer = jdbcTemplate.queryForMap(sql, cusId);

        String customerName = (String) customer.get("CUS_NAME");
        String customerEmail = (String) customer.get("CUS_EMAIL");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customerEmail);
        message.setSubject("Order Dispatched");

        String body = "Dear " + customerName + ",\n\n" +
                "Your order with ID " + ordId + " has been dispatched.\n" +
                "We will notify you once it is out for delivery.\n\n" +
                "Best regards,\n" +
                "The HotByte Team";
  message.setText(body);

  mailSender.send(message);
  System.out.println("Order dispatch notification email sent to: " + customerEmail);
}

// Method to set estimated delivery time for an order
public String setEstimatedDeliveryTime(int ordId, String estimatedTime) {
  Orders order = ordersRepo.findById(ordId).orElse(null);
  if (order == null) {
      return "Order not found.";
  }

  order.setEstimatedDeliveryTime(estimatedTime);
  ordersRepo.save(order);

  sendEstimatedDeliveryTimeEmail(order.getCusId(), ordId, estimatedTime);

  return "Estimated delivery time set successfully!";
}

private void sendEstimatedDeliveryTimeEmail(int cusId, int ordId, String estimatedTime) {
  String sql = "SELECT CUS_NAME, CUS_EMAIL FROM customer WHERE CUS_ID = ?";
  Map<String, Object> customer = jdbcTemplate.queryForMap(sql, cusId);

  String customerName = (String) customer.get("CUS_NAME");
  String customerEmail = (String) customer.get("CUS_EMAIL");

  SimpleMailMessage message = new SimpleMailMessage();
  message.setTo(customerEmail);
  message.setSubject("Estimated Delivery Time Updated");

  String body = "Dear " + customerName + ",\n\n" +
                "The estimated delivery time for your order with ID " + ordId + " has been updated to " + estimatedTime + ".\n\n" +
                "Thank you for your patience.\n\n" +
                "Best regards,\n" +
                "The HotByte Team";
  message.setText(body);

  mailSender.send(message);
  System.out.println("Estimated delivery time update email sent to: " + customerEmail);
}
}

