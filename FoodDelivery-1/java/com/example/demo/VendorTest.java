package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VendorTest {

    @Test
    public void testConstructor() {
        // Default constructor
        Vendor vendor = new Vendor();
        assertNotNull(vendor);

        // Parameterized constructor
        Vendor vendorNew = new Vendor(1, "Balan", "8554535400", "balan89", "chennai@123", "balan@gmail.com");
        assertNotNull(vendorNew);
        assertEquals(1, vendorNew.getVenId());
        assertEquals("Balan", vendorNew.getVenName());
        assertEquals("8554535400", vendorNew.getVenPhoneNo());
        assertEquals("balan89", vendorNew.getVenUserName());
        assertEquals("chennai@123", vendorNew.getVenPassword());
        assertEquals("balan@gmail.com", vendorNew.getVenEmail());
    }

    @Test
    public void testGetterSetter() {
        Vendor vendor = new Vendor();
        vendor.setVenId(1);
        vendor.setVenName("Balan");
        vendor.setVenPhoneNo("8554535400");
        vendor.setVenUserName("balan89");
        vendor.setVenPassword("chennai@123");
        vendor.setVenEmail("balan@gmail.com");

        assertEquals(1, vendor.getVenId());
        assertEquals("Balan", vendor.getVenName());
        assertEquals("8554535400", vendor.getVenPhoneNo());
        assertEquals("balan89", vendor.getVenUserName());
        assertEquals("chennai@123", vendor.getVenPassword());
        assertEquals("balan@gmail.com", vendor.getVenEmail());
    }

   
}
