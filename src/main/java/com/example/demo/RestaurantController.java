package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/restaurant")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    
    @PostMapping(value="/addRestaurant")
    public void addRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
    }
    
    @GetMapping(value="/showRestaurants")
    public List<Restaurant> showRestaurants() {
        return restaurantService.showRestaurants();
    }
    
    @GetMapping(value="/searchRestaurant/{id}")
    public Restaurant searchById(@PathVariable int id) {
        return restaurantService.searchById(id);
    }
    
    @DeleteMapping(value="/deleteRestaurant/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);
    }
}
