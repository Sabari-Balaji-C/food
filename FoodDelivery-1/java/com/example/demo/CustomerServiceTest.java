package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowCustomer() {
        Customer customer = new Customer(1, "Prasanna Pappu", "9381413089", "prassucp", "hexaware@", "prassucp@gmail.com");
        when(customerRepo.findAll()).thenReturn(Collections.singletonList(customer));

        List<Customer> customers = customerService.showCustomer();
        assertEquals(1, customers.size());
        assertEquals("Prasanna Pappu", customers.get(0).getCusName());
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer(1,"Prasanna Pappu", "9381413089", "prassucp", "hexaware@", "prassucp@gmail.com");
        when(customerRepo.save(customer)).thenReturn(customer);

        customerService.addCustomer(customer);
        verify(customerRepo, times(1)).save(customer);
    }

    @Test
    void testLoginSuccessful() {
        String user = "prassucp";
        String pwd = "hexaware@";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Collections.singletonList(1));

        int result = customerService.login(user, pwd);
        assertEquals(1, result);
    }

    @Test
    void testLoginUnsuccessful() {
        String user = "prassucp";
        String pwd = "wrongpassword";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Collections.singletonList(0));

        int result = customerService.login(user, pwd);
        assertEquals(0, result);
    }

    @Test
    void testSearchByUserNameFound() {
        String userName = "prassucp";
        Customer customer = new Customer(1, "Prasanna Pappu", "9381413089", "prassucp", "hexaware@", "prassucp@gmail.com");
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Collections.singletonList(customer));

        Customer result = customerService.searchByUserName(userName);
        assertNotNull(result);
        assertEquals("Prasanna Pappu", result.getCusName());
    }

    @Test
    void testSearchByUserNameNotFound() {
        String userName = "unknown";
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Collections.emptyList());

        Customer result = customerService.searchByUserName(userName);
        assertNull(result);
    }

    @Test
    void testSearchCustomerFound() {
        int id = 1;
        Customer customer = new Customer(1, "Prasanna Pappu", "9381413089", "prassucp", "hexaware@", "prassucp@gmail.com");
        when(customerRepo.findById(id)).thenReturn(Optional.of(customer));

        Customer result = customerService.searchCustomer(id);
        assertNotNull(result);
        assertEquals("Prasanna Pappu", result.getCusName());
    }

    @Test
    void testSearchCustomerNotFound() {
        int id = 999;
        when(customerRepo.findById(id)).thenReturn(Optional.empty());

        Customer result = customerService.searchCustomer(id);
        assertNull(result);
    }
}
