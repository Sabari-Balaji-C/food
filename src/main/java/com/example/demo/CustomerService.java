package com.example.demo;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Customer> showCustomer() {
		return customerRepo.findAll();
	}
	
	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}
	
	public int login(String user, String pwd) {
		String cmd = "select count(*) cnt from Customer where "
				+ " CUS_USERNAME = ? AND CUS_PASSWORD = ?";
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
	public Customer searchByUserName(String userName) {
		String cmd = "select * from Customer where CUS_USERNAME = ?";
		List<Customer> customerList = jdbcTemplate.query(cmd, new Object[] {userName}, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer();
				customer.setCusId(rs.getInt("CUS_ID"));
				customer.setCusName(rs.getString("CUS_NAME"));
				customer.setCusPhoneNo(rs.getString("CUS_PHN_NO"));
				customer.setCusUserName(rs.getString("CUS_USERNAME"));
				customer.setCusPassword(rs.getString("CUS_PASSWORD"));
				customer.setCusEmail(rs.getString("CUS_EMAIL"));
				return customer;
			}
			
		});
		
		if (customerList.size()==1) {
			return customerList.get(0);
		}
		return null;
	}
	public Customer searchCustomer(int id) {
		//return customerRepo.findById(id).get();
		Optional<Customer> customer = customerRepo.findById(id);
	    return customer.orElse(null);  
	
	}
	public void deleteCustomer(int id) {
	    customerRepo.deleteById(id);
	}
}
