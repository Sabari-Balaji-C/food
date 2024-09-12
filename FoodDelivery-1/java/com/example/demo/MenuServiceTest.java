package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

class MenuServiceTest {

    @Mock
    private MenuRepository menuRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMenu() {
        Menu menu = new Menu(1, "CHICKEN BIRIYANI", 856.00, "750", "NON-VEG", 1);

        menuService.addMenu(menu);

        verify(menuRepo, times(1)).save(menu);
    }

    @Test
    void testShowMenuByRestaurant() {
        int restId = 1;
        Menu menu1 = new Menu(1, "CHICKEN BIRIYANI", 856.00, "750", "NON-VEG", restId);
        Menu menu2 = new Menu(2, "CHICKEN 65", 674.00, "500", "NON-VEG", restId);
        List<Menu> menus = Arrays.asList(menu1, menu2);

        when(jdbcTemplate.query(eq("SELECT * FROM Menu WHERE REST_ID = ?"),
                any(Object[].class),
                any(RowMapper.class)))
                .thenReturn(menus);

        List<Menu> result = menuService.showMenuByRestaurant(restId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("CHICKEN BIRIYANI", result.get(0).getMenItem());
    }

    @Test
    void testShowMenuByRestaurantEmpty() {
        int restId = 999;

        when(jdbcTemplate.query(eq("SELECT * FROM Menu WHERE REST_ID = ?"),
                any(Object[].class),
                any(RowMapper.class)))
                .thenReturn(Arrays.asList());

        List<Menu> result = menuService.showMenuByRestaurant(restId);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
