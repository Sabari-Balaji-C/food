package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowRestaurants() {
        Restaurant restaurant1 = new Restaurant(1, "Restaurant A", "Location A", "1234567890");
        Restaurant restaurant2 = new Restaurant(2, "Restaurant B", "Location B", "0987654321");
        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);

        when(restaurantRepo.findAll()).thenReturn(restaurants);

        List<Restaurant> result = restaurantService.showRestaurants();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Restaurant A", result.get(0).getRestName());
    }

    @Test
    void testAddRestaurant() {
        Restaurant restaurant = new Restaurant(1, "Restaurant C", "Location C", "1122334455");

        restaurantService.addRestaurant(restaurant);

        verify(restaurantRepo, times(1)).save(restaurant);
    }

    @Test
    void testSearchByIdFound() {
        int id = 1;
        Restaurant restaurant = new Restaurant(id, "Restaurant A", "Location A", "1234567890");
        when(jdbcTemplate.query(eq("select * from Restaurant where REST_ID = ?"),
                any(Object[].class),
                any(RowMapper.class)))
                .thenReturn(Arrays.asList(restaurant));

        Restaurant result = restaurantService.searchById(id);
        assertNotNull(result);
        assertEquals("Restaurant A", result.getRestName());
    }

    @Test
    void testSearchByIdNotFound() {
        int id = 999;
        when(jdbcTemplate.query(eq("select * from Restaurant where REST_ID = ?"),
                any(Object[].class),
                any(RowMapper.class)))
                .thenReturn(Arrays.asList());

        Restaurant result = restaurantService.searchById(id);
        assertNull(result);
    }
}
