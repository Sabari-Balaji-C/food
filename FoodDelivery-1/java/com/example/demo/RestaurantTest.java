package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestaurantTest {

    @Test
    public void testConstructor() {
        // Default constructor
        Restaurant restaurant = new Restaurant();
        assertNotNull(restaurant);

        // Parameterized constructor
        Restaurant restaurantNew = new Restaurant(1, "Restaurant A", "Location A", "1234567890");
        assertNotNull(restaurantNew);
        assertEquals(1, restaurantNew.getRestId());
        assertEquals("Restaurant A", restaurantNew.getRestName());
        assertEquals("Location A", restaurantNew.getRestLocation());
        assertEquals("1234567890", restaurantNew.getRestPhoneNo());
    }

    @Test
    public void testGetterSetter() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestId(1);
        restaurant.setRestName("Restaurant A");
        restaurant.setRestLocation("Location A");
        restaurant.setRestPhoneNo("1234567890");

        assertEquals(1, restaurant.getRestId());
        assertEquals("Restaurant A", restaurant.getRestName());
        assertEquals("Location A", restaurant.getRestLocation());
        assertEquals("1234567890", restaurant.getRestPhoneNo());
    }

    
}
