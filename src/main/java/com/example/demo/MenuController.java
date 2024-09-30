package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/menu")
@CrossOrigin(origins = "http://localhost:4200")
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
    
    @PostMapping(value="/modifyMenu/{menId}")
    public Menu modifyMenu(@PathVariable int menId, @RequestBody Menu updatedMenu) {
        return menuService.updateMenu(menId, updatedMenu);
    }
    
    @PostMapping(value="/deleteMenu/{menId}")
    public void deleteMenu(@PathVariable int menId) {
        menuService.deleteMenu(menId);
    }
    
    @GetMapping("/getMenuPrice/{menId}")
    public ResponseEntity<Double> getMenuPrice(@PathVariable int menId) {
        Double price = menuService.getMenuPrice(menId); // Calls the service method which now handles null cases
        if (price != null) {
            return ResponseEntity.ok(price); // Returns the price if found
        } else {
            return ResponseEntity.notFound().build(); // Returns 404 if price is not found
        }
    }


    
}


