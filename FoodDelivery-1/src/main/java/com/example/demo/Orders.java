package com.example.demo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "ORD_ID")
    private int ordId;

    @Column(name = "CUS_ID")
    private int cusId;

    @Column(name = "VEN_ID")
    private int venId;

    @Column(name = "MEN_ID")
    private int menId;

    @Column(name = "WAL_SOURCE")
    private String walSource;

    @Column(name = "ORD_DATE")
    private java.sql.Date ordDate;

    @Column(name = "ORD_QUANTITY")
    private int ordQuantity;

    @Column(name = "ORD_BILLAMOUNT")
    private double ordBillAmount;

    @Column(name = "ORD_STATUS")
    private String ordStatus;

    @Column(name = "ORD_COMMENTS")
    private String ordComments;

	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getVenId() {
		return venId;
	}

	public void setVenId(int venId) {
		this.venId = venId;
	}

	public int getMenId() {
		return menId;
	}

	public void setMenId(int menId) {
		this.menId = menId;
	}

	public String getWalSource() {
		return walSource;
	}

	public void setWalSource(String walSource) {
		this.walSource = walSource;
	}

	public java.sql.Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(java.sql.Date ordDate) {
		this.ordDate = ordDate;
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

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public String getOrdComments() {
		return ordComments;
	}

	public void setOrdComments(String ordComments) {
		this.ordComments = ordComments;
	}

	public Orders(int ordId, int cusId, int venId, int menId, String walSource, Date ordDate, int ordQuantity,
			double ordBillAmount, String ordStatus, String ordComments) {
		super();
		this.ordId = ordId;
		this.cusId = cusId;
		this.venId = venId;
		this.menId = menId;
		this.walSource = walSource;
		this.ordDate = ordDate;
		this.ordQuantity = ordQuantity;
		this.ordBillAmount = ordBillAmount;
		this.ordStatus = ordStatus;
		this.ordComments = ordComments;
	}

	public Orders() {
		
		// TODO Auto-generated constructor stub
	}
}

    
