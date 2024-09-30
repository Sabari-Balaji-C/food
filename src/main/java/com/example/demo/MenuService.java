package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepo;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void addMenu(Menu menu) {
        menuRepo.save(menu);
    }
    
    public List<Menu> showMenuByRestaurant(int restId) {
        String cmd = "SELECT * FROM Menu WHERE REST_ID = ?";
        return jdbcTemplate.query(cmd, new Object[]{restId}, new RowMapper<Menu>() {

            @Override
            public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
                Menu menu = new Menu();
                menu.setMenId(rs.getInt("MEN_ID"));
                menu.setMenItem(rs.getString("MEN_ITEM"));
                menu.setMenPrice(rs.getDouble("MEN_PRICE"));
                menu.setMenCalories(rs.getString("MEN_CALORIES"));
                menu.setMenSpeciality(rs.getString("MEN_SPECIALITY"));
                menu.setMenCategory(rs.getString("MEN_CATEGORY"));
                menu.setRestId(rs.getInt("REST_ID"));
                return menu;
            }
        });
   
    }
    public Menu updateMenu(int menId, Menu updatedMenu) {
        Menu existingMenu = menuRepo.findById(menId).orElse(null);
        if (existingMenu != null) {
            existingMenu.setMenItem(updatedMenu.getMenItem());
            existingMenu.setMenPrice(updatedMenu.getMenPrice());
            existingMenu.setMenCalories(updatedMenu.getMenCalories());
            existingMenu.setMenSpeciality(updatedMenu.getMenSpeciality());
            existingMenu.setMenCategory(updatedMenu.getMenCategory());
            existingMenu.setRestId(updatedMenu.getRestId());
            menuRepo.save(existingMenu);
        }
        return existingMenu;
    }

    public void deleteMenu(int menId) {
        menuRepo.deleteById(menId);
    }
    
    public Double getMenuPrice(int menId) {
        return menuRepo.findMenuPriceById(menId);  // Will return null if not found
    }
    
}

    
