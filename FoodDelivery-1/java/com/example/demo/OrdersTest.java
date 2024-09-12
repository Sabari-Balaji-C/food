/*package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

public class OrdersTest {

    @Test
    public void testConstructor() {
        // Default constructor
        Orders orders = new Orders();
        assertNotNull(orders);

        // Parameterized constructor
        Orders ordersNew = new Orders(1, 1, 1, 1, "PAYTM", new Date(System.currentTimeMillis()), 1, 856.00, "ACCEPTED", "Fast Delivery");
        assertNotNull(ordersNew);
        assertEquals(1, ordersNew.getOrdId());
        assertEquals(1, ordersNew.getCusId());
        assertEquals(1, ordersNew.getVenId());
        assertEquals(1, ordersNew.getMenId());
        assertEquals("PAYTM", ordersNew.getWalSource());
        assertEquals(new Date(System.currentTimeMillis()), ordersNew.getOrdDate());  // Note: You may need to adjust this comparison depending on the actual date value
        assertEquals(1, ordersNew.getOrdQuantity());
        assertEquals(856.00, ordersNew.getOrdBillAmount());
        assertEquals("ACCEPTED", ordersNew.getOrdStatus());
        assertEquals("Fast Delivery", ordersNew.getOrdComments());
    }

    @Test
    public void testGetterSetter() {
        Orders orders = new Orders();
        orders.setOrdId(1);
        orders.setCusId(1);
        orders.setVenId(1);
        orders.setMenId(1);
        orders.setWalSource("PAYTM");
        orders.setOrdDate(new Date(System.currentTimeMillis()));
        orders.setOrdQuantity(1);
        orders.setOrdBillAmount(856.00);
        orders.setOrdStatus("ACCEPTED");
        orders.setOrdComments("Fast Delivery");

        assertEquals(1, orders.getOrdId());
        assertEquals(1, orders.getCusId());
        assertEquals(1, orders.getVenId());
        assertEquals(1, orders.getMenId());
        assertEquals("PAYTM", orders.getWalSource());
        assertEquals(new Date(System.currentTimeMillis()), orders.getOrdDate());  // Note: You may need to adjust this comparison depending on the actual date value
        assertEquals(1, orders.getOrdQuantity());
        assertEquals(856.00, orders.getOrdBillAmount());
        assertEquals("ACCEPTED", orders.getOrdStatus());
        assertEquals("Fast Delivery", orders.getOrdComments());
    }

}*/
