package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin")
public class Admin {

    @Id
    @Column(name="ADMIN_ID")
    private int adminId;
    @Column(name="ADMIN_NAME")
    private String adminname;
    @Column(name="ADMIN_PHN_NO")
    private String adminPhoneNo;
    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="ADMIN_EMAIL")
    private String adminEmail;
    @Column(name="ROLE")
    private String role;
    

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminname;
    }

    public void setAdminName(String adminName) {
        this.adminname = adminName;
    }

    public String getAdminPhoneNo() {
        return adminPhoneNo;
    }

    public void setAdminPhoneNo(String adminPhoneNo) {
        this.adminPhoneNo = adminPhoneNo;
    }

    

    

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
    
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    

	
	public Admin(int adminId, String adminName, String adminPhoneNo, String username, String password,
			String adminEmail, String role) {
		super();
		this.adminId = adminId;
		this.adminname = adminname;
		this.adminPhoneNo = adminPhoneNo;
		this.username = username;
		this.password = password;
		this.adminEmail = adminEmail;
		this.role = role;
	}

	public Admin() {
        // Default constructor
    }
}
