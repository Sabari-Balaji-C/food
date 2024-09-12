package com.example.demo;

public class CartItem {
    private int menId;
    private int ordQuantity;
    private double ordBillAmount;
	public int getMenId() {
		return menId;
	}
	public void setMenId(int menId) {
		this.menId = menId;
	}
	public int getOrdQuantity() {
		return ordQuantity;
	}
	public void setOrdQuantity(int ordQuantity) {
		this.ordQuantity = ordQuantity;
	}
	public double getOrdBillAmount() {
		return ordBillAmount;
	}
	public void setOrdBillAmount(double ordBillAmount) {
		this.ordBillAmount = ordBillAmount;
	}
	public CartItem(int menId, int ordQuantity, double ordBillAmount) {
		super();
		this.menId = menId;
		this.ordQuantity = ordQuantity;
		this.ordBillAmount = ordBillAmount;
	}
	public CartItem() {
		
		// TODO Auto-generated constructor stub
	}

}
   