package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	    @Id
	    @Column(name="CART_ID")
	    private int cartId;
	    @Column(name="CUS_ID")
	    private int cusId;
	    @Column(name="MEN_ID")
	    private int menId;
	    @Column(name="ITEM_NAME")
	    private String itemName;
	    @Column(name="PRICE")
	    private double price;
	    
	    @Column(name="QUANTITY")
	    private int quantity;


		// Getters and Setters
	    public int getCartId() {
	        return cartId;
	    }

	    public void setCartId(int cartId) {
	        this.cartId = cartId;
	    }

	    public int getCusId() {
	        return cusId;
	    }

	    public void setCusId(int cusId) {
	        this.cusId = cusId;
	    }

	    public int getMenId() {
	        return menId;
	    }

	    public void setMenId(int menId) {
	        this.menId = menId;
	    }

	    public String getItemName() {
	        return itemName;
	    }

	    public void setItemName(String itemName) {
	        this.itemName = itemName;
	    }
	    
	    

	    public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

		@Override
		public String toString() {
			return "Cart [cartId=" + cartId + ", cusId=" + cusId + ", menId=" + menId + ", itemName=" + itemName
					+ ", price=" + price + ", quantity=" + quantity + "]";
		}

		
	}
