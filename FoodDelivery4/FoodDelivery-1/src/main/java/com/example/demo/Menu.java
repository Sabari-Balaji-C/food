package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Menu")
public class Menu {

    @Id
    @Column(name="MEN_ID")
    private int menId;

    @Column(name="MEN_ITEM")
    private String menItem;

    @Column(name="MEN_PRICE")
    private double menPrice;
    
    @Column(name="MEN_CALORIES")
    private String menCalories;
    
    @Column(name="MEN_SPECIALITY")
    private String menSpeciality;

    @Column(name="REST_ID")
    private int restId;

	public int getMenId() {
		return menId;
	}

	public void setMenId(int menId) {
		this.menId = menId;
	}

	public String getMenItem() {
		return menItem;
	}

	public void setMenItem(String menItem) {
		this.menItem = menItem;
	}

	public double getMenPrice() {
		return menPrice;
	}

	public void setMenPrice(double menPrice) {
		this.menPrice = menPrice;
	}

	public String getMenCalories() {
		return menCalories;
	}

	public void setMenCalories(String menCalories) {
		this.menCalories = menCalories;
	}

	public String getMenSpeciality() {
		return menSpeciality;
	}

	public void setMenSpeciality(String menSpeciality) {
		this.menSpeciality = menSpeciality;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public Menu(int menId, String menItem, double menPrice, String menCalories, String menSpeciality, int restId) {
		super();
		this.menId = menId;
		this.menItem = menItem;
		this.menPrice = menPrice;
		this.menCalories = menCalories;
		this.menSpeciality = menSpeciality;
		this.restId = restId;
	}

	public Menu() {
		
		// TODO Auto-generated constructor stub
	}
}
    

	    