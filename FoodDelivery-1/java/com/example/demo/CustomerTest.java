package com.example.demo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.Customer;

public class CustomerTest {

    @Test
    public void testConstructor() {
        // Default constructor
        Customer customer = new Customer();
        assertNotNull(customer);

        // Parameterized constructor
        Customer customerNew = new Customer(1, "Prasanna Pappu", "9381413089", "prassucp", "hexaware@", "prassucp@gmail.com");
        assertNotNull(customerNew);
        assertEquals(1, customerNew.getCusId());
        assertEquals("Prasanna Pappu", customerNew.getCusName());
        assertEquals("9381413089", customerNew.getCusPhoneNo());
        assertEquals("prassucp", customerNew.getCusUserName());
        assertEquals("hexaware@", customerNew.getCusPassword());
        assertEquals("prassucp@gmail.com", customerNew.getCusEmail());
    }

    @Test
    public void testGetterSetter() {
        Customer customer = new Customer();
        customer.setCusId(1);
        customer.setCusName("Prasanna Pappu");
        customer.setCusPhoneNo("9381413089");
        customer.setCusUserName("prassucp");
        customer.setCusPassword("hexaware@");
        customer.setCusEmail("prassucp@gmail.com");

        assertEquals(1, customer.getCusId());
        assertEquals("Prasanna Pappu", customer.getCusName());
        assertEquals("9381413089", customer.getCusPhoneNo());
        assertEquals("prassucp", customer.getCusUserName());
        assertEquals("hexaware@", customer.getCusPassword());
        assertEquals("prassucp@gmail.com", customer.getCusEmail());
    }
}
