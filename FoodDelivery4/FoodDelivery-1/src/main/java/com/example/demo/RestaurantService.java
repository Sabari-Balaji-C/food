package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepo;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Restaurant> showRestaurants() {
        return restaurantRepo.findAll();
    }
    
    public void addRestaurant(Restaurant restaurant) {
        restaurantRepo.save(restaurant);
    }
    
    public Restaurant searchById(int id) {
        String cmd = "select * from Restaurant where REST_ID = ?";
        List<Restaurant> restaurantList = jdbcTemplate.query(cmd, new Object[] {id}, new RowMapper<Restaurant>() {

            @Override
            public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestId(rs.getInt("REST_ID"));
                restaurant.setRestName(rs.getString("REST_NAME"));
                restaurant.setRestLocation(rs.getString("REST_LOCATION"));
                restaurant.setRestPhoneNo(rs.getString("REST_PHN_NO"));
                return restaurant;
            }
            
        });
        
        if (restaurantList.size() == 1) {
            return restaurantList.get(0);
        }
        return null;
    }
}
