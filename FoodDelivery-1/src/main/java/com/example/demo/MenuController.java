package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;
    
    @PostMapping(value="/addMenu")
    public void addMenu(@RequestBody Menu menu) {
        menuService.addMenu(menu);
    }

    @GetMapping(value="/showMenuByRestaurant/{restId}")
    public List<Menu> showMenuByRestaurant(@PathVariable int restId) {
        return menuService.showMenuByRestaurant(restId);
    }
}

