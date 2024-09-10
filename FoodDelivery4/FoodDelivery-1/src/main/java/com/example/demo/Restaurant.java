package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Restaurant")
public class Restaurant {

	@Id
	@Column(name="REST_ID")
	private int restId;
	@Column(name="REST_NAME")
	private String restName;
	@Column(name="REST_LOCATION")
	private String restLocation;
	@Column(name="REST_PHN_NO")
	private String restPhoneNo;
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestLocation() {
		return restLocation;
	}
	public void setRestLocation(String restLocation) {
		this.restLocation = restLocation;
	}
	public String getRestPhoneNo() {
		return restPhoneNo;
	}
	public void setRestPhoneNo(String restPhoneNo) {
		this.restPhoneNo = restPhoneNo;
	}
	public Restaurant(int restId, String restName, String restLocation, String restPhoneNo) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.restLocation = restLocation;
		this.restPhoneNo = restPhoneNo;
	}
	public Restaurant() {
		
		// TODO Auto-generated constructor stub
	}
}
	
	
	
	
	