package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Vendor> showVendor() {
		return vendorRepo.findAll();
	}
	
	public void addVendor(Vendor vendor) {
		vendorRepo.save(vendor);
	}
	
	public int login(String user, String pwd) {
		String cmd = "select count(*) cnt from Vendor where "
				+ " VEN_USERNAME = ? AND VEN_PASSWORD = ?";
		List<Object> counts = jdbcTemplate.query(cmd, new Object[] {user, pwd}, new RowMapper<Object>() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Object ob = rs.getInt("cnt");
				return ob;
			}
			
		});
		int i = (Integer)counts.get(0);
		return i;
	}
	
	public Vendor searchByVendorName(String userName) {
		String cmd = "select * from Vendor where VEN_USERNAME = ?";
		List<Vendor> vendorList = jdbcTemplate.query(cmd, new Object[] {userName}, new RowMapper<Vendor>() {

			@Override
			public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Vendor vendor = new Vendor();
				vendor.setVenId(rs.getInt("VEN_ID"));
				vendor.setVenName(rs.getString("VEN_NAME"));
				vendor.setVenPhoneNo(rs.getString("VEN_PHN_NO"));
				vendor.setVenUserName(rs.getString("VEN_USERNAME"));
				vendor.setVenPassword(rs.getString("VEN_PASSWORD"));
				vendor.setVenEmail(rs.getString("VEN_EMAIL"));
				return vendor;
			}
			
		});
		
		if (vendorList.size()==1) {
			return vendorList.get(0);
		}
		return null;
	}
	public Vendor searchVendor(int id) {
		return vendorRepo.findById(id).get();
	}
}
