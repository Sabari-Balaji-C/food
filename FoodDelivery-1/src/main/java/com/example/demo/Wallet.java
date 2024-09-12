package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Wallet")
public class Wallet {

	@Id
	@Column(name="WAL_ID")
	private int walletId;
	@Column(name="CUS_ID")
	private int custId;
	@Column(name="WAL_AMOUNT")
	private double walAmount;
	@Column(name="WAL_SOURCE")
	private String walSource;
	public int getWalletId() {
		return walletId;
	}
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public double getWalAmount() {
		return walAmount;
	}
	public void setWalAmount(double walAmount) {
		this.walAmount = walAmount;
	}
	public String getWalSource() {
		return walSource;
	}
	public void setWalSource(String walSource) {
		this.walSource = walSource;
	}
	public Wallet(int walletId, int custId, double walAmount, String walSource) {
		this.walletId = walletId;
		this.custId = custId;
		this.walAmount = walAmount;
		this.walSource = walSource;
	}
	public Wallet() {
		// TODO Auto-generated constructor stub
	}
	
	
}
