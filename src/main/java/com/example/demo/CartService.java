package com.example.demo;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private MenuService menuService;
    

    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    public void addItem(Cart item) {
        cartRepository.save(item);
    }
    

    

    public void removeItem(int cusId, int menId) {
    	String cmd="delete from cart where CUS_ID=? AND MEN_ID=?";
    	jdbcTemplate.update(cmd, cusId, menId);    }

    public List<Cart> getCartItems(int cusId) {
        String cmd = "select * from cart where CUS_ID = ?";
        return jdbcTemplate.query(cmd, new Object[] {cusId}, new BeanPropertyRowMapper<>(Cart.class));
    }
    


	/*public List<Cart> showItems() {
		return cartRepository.findAll();
	}*/


	public void clearAll() {
		 cartRepository.deleteAll();
		
	}
	
	public void updateCartItem(Cart cart) {
	    String cmd = "UPDATE cart SET QUANTITY = ? WHERE CUS_ID = ? AND MEN_ID = ?";
	    jdbcTemplate.update(cmd, cart.getQuantity(), cart.getCusId(), cart.getMenId());
	}
	
	public double calculateTotalCost(int cusId) {
	    List<Cart> items = getCartItems(cusId);
	    double totalCost = 0;
	    for (Cart item : items) {
	        totalCost += item.getPrice() * item.getQuantity();
	    }
	    return totalCost;
	}


}