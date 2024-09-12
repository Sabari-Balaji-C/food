package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Vendor")
public class Vendor {
	
	@Id
	@Column(name="VEN_ID")
	private int venId;
	@Column(name="VEN_NAME")
	private String venName;
	@Column(name="VEN_PHN_NO")
	private String venPhoneNo;
	@Column(name="VEN_USERNAME")
	private String venUserName;
	@Column(name="VEN_PASSWORD")
	private String venPassword;
	@Column(name="VEN_EMAIL")
	private String venEmail;
	public int getVenId() {
		return venId;
	}
	public void setVenId(int venId) {
		this.venId = venId;
	}
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public String getVenPhoneNo() {
		return venPhoneNo;
	}
	public void setVenPhoneNo(String venPhoneNo) {
		this.venPhoneNo = venPhoneNo;
	}
	public String getVenUserName() {
		return venUserName;
	}
	public void setVenUserName(String venUserName) {
		this.venUserName = venUserName;
	}
	public String getVenPassword() {
		return venPassword;
	}
	public void setVenPassword(String venPassword) {
		this.venPassword = venPassword;
	}
	public String getVenEmail() {
		return venEmail;
	}
	public void setVenEmail(String venEmail) {
		this.venEmail = venEmail;
	}
	public Vendor(int venId, String venName, String venPhoneNo, String venUserName, String venPassword,
			String venEmail) {
		super();
		this.venId = venId;
		this.venName = venName;
		this.venPhoneNo = venPhoneNo;
		this.venUserName = venUserName;
		this.venPassword = venPassword;
		this.venEmail = venEmail;
	}
	public Vendor() {
		
		// TODO Auto-generated constructor stub
	}
}
	
	