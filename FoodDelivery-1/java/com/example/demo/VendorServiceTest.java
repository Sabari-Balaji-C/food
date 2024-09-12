package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

class VendorServiceTest {

    @Mock
    private VendorRepository vendorRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddVendor() {
        Vendor vendor = new Vendor(1, "Balan", "8554535400", "balan89", "chennai@123", "balan@gmail.com");

        vendorService.addVendor(vendor);

        verify(vendorRepo, times(1)).save(vendor);
    }

    @Test
    void testShowVendor() {
        Vendor vendor1 = new Vendor(1, "Balan", "8554535400", "balan89", "chennai@123", "balan@gmail.com");
        Vendor vendor2 = new Vendor(2, "Anukriti", "9184532455", "anu56", "bordia@123", "anu.delhi@gmail.com");
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorRepo.findAll()).thenReturn(vendors);

        List<Vendor> result = vendorService.showVendor();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Balan", result.get(0).getVenName());
    }

    @Test
    void testLoginSuccessful() {
        String user = "balan89";
        String pwd = "chennai@123";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Collections.singletonList(1));

        int result = vendorService.login(user, pwd);
        assertEquals(1, result);
    }

    @Test
    void testLoginUnsuccessful() {
        String user = "balan89";
        String pwd = "wrongpassword";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Collections.singletonList(0));

        int result = vendorService.login(user, pwd);
        assertEquals(0, result);
    }



    @Test
    void testSearchByVendorNameFound() {
        String username = "balan89";
        Vendor vendor = new Vendor(1, "Balan", "8554535400", "balan89", "chennai@123", "balan@gmail.com");
        String cmd = "select * from Vendor where VEN_USERNAME = ?";
        when(jdbcTemplate.query(eq(cmd), any(Object[].class), any(RowMapper.class)))
            .thenReturn(Arrays.asList(vendor));

        Vendor result = vendorService.searchByVendorName(username);
        assertNotNull(result);
        assertEquals("Balan", result.getVenName());
    }

    @Test
    void testSearchByVendorNameNotFound() {
        String username = "unknownuser";
        String cmd = "select * from Vendor where VEN_USERNAME = ?";
        when(jdbcTemplate.query(eq(cmd), any(Object[].class), any(RowMapper.class)))
            .thenReturn(Arrays.asList());

        Vendor result = vendorService.searchByVendorName(username);
        assertNull(result);
    }

    @Test
    void testSearchVendor() {
        int id = 1;
        Vendor vendor = new Vendor(1, "Balan", "8554535400", "balan89", "chennai@123", "balan@gmail.com");

        when(vendorRepo.findById(id)).thenReturn(Optional.of(vendor));

        Vendor result = vendorService.searchVendor(id);
        assertNotNull(result);
        assertEquals("Balan", result.getVenName());
    }
}
