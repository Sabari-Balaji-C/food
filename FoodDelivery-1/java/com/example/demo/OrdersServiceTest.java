/*package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

public class OrdersServiceTest {

    @InjectMocks
    private OrdersService ordersService;

    @Mock
    private OrdersRepository ordersRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private WalletService walletService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrderSuccess() {
        Orders order = new Orders(1, 1, 1, 1, "PAYTM", new Date(System.currentTimeMillis()), 1, 0.0, "PENDING", "Test");
        when(walletService.getWalletAmount(1, "PAYTM")).thenReturn(1000.00);
        when(jdbcTemplate.queryForObject("SELECT MEN_PRICE FROM menu WHERE MEN_ID = ?", new Object[]{1}, Double.class)).thenReturn(500.00);

        String result = ordersService.placeOrder(order);

        assertEquals("Order placed successfully!", result);
        verify(ordersRepo, times(1)).save(order);
        verify(walletService, times(1)).updateWalletAmount(1, "PAYTM", 500.00);
    }

    @Test
    public void testPlaceOrderInsufficientBalance() {
        Orders order = new Orders(1, 1, 1, 1, "PAYTM", new Date(System.currentTimeMillis()), 1, 0.0, "PENDING", "Test");
        when(walletService.getWalletAmount(1, "PAYTM")).thenReturn(200.00);
        when(jdbcTemplate.queryForObject("SELECT MEN_PRICE FROM menu WHERE MEN_ID = ?", new Object[]{1}, Double.class)).thenReturn(500.00);

        String result = ordersService.placeOrder(order);

        assertEquals("Insufficient wallet balance. Order denied.", result);
        verify(ordersRepo, times(1)).save(order);
        verify(walletService, never()).updateWalletAmount(anyInt(), anyString(), anyDouble());
    }

    @Test
    public void testCalculateTotalBillAmount() {
        when(jdbcTemplate.queryForObject("SELECT MEN_PRICE FROM menu WHERE MEN_ID = ?", new Object[]{1}, Double.class)).thenReturn(100.00);

        double result = ordersService.calculateTotalBillAmount(1, 3);

        assertEquals(300.00, result);
    }

    @Test
    public void testShowOrders() {
        Orders order1 = new Orders(1, 1, 1, 1, "PAYTM", new Date(System.currentTimeMillis()), 1, 500.00, "PENDING", "Test");
        Orders order2 = new Orders(2, 2, 2, 2, "CREDIT_CARD", new Date(System.currentTimeMillis()), 2, 1000.00, "ACCEPTED", "Test");
        when(ordersRepo.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Orders> orders = ordersService.showOrders();

        assertEquals(2, orders.size());
    }
}*/
