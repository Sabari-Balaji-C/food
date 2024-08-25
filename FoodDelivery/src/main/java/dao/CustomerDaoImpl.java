package dao;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Customer;
import util.DBConnection;
public class CustomerDaoImpl implements CustomerDao{
	public void addCustomer(Customer customer) throws ClassNotFoundException {
        String sql = "INSERT INTO customer (CUS_NAME, CUS_PHN_NO, CUS_USERNAME, CUS_PASSWORD, CUS_EMAIL) VALUES ( ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
        	stmt.setString(1, customer.getCusName());
            stmt.setString(2, customer.getCusPhoneNo());
            stmt.setString(3, customer.getCusUsername());
            stmt.setString(4, customer.getCusPassword());
            stmt.setString(5, customer.getCusEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public Customer authenticateCustomer(String username, String password) throws ClassNotFoundException{
	    String sql = "SELECT * FROM customer WHERE CUS_USERNAME = ? AND CUS_PASSWORD = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, username);
	        stmt.setString(2, password);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new Customer(rs.getInt("CUS_ID"),
	                                    rs.getString("CUS_NAME"),
	                                    rs.getString("CUS_PHN_NO"),
	                                    rs.getString("CUS_USERNAME"),
	                                    rs.getString("CUS_PASSWORD"),
	                                    rs.getString("CUS_EMAIL"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public Customer searchCustomerByUsername(String username) throws ClassNotFoundException{
        String sql = "SELECT * FROM customer WHERE CUS_USERNAME = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(rs.getInt("CUS_ID"),
                                        rs.getString("CUS_NAME"),
                                        rs.getString("CUS_PHN_NO"),
                                        rs.getString("CUS_USERNAME"),
                                        rs.getString("CUS_PASSWORD"),
                                        rs.getString("CUS_EMAIL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer searchCustomerById(int customerId)throws ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE CUS_ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(rs.getInt("CUS_ID"),
                                        rs.getString("CUS_NAME"),
                                        rs.getString("CUS_PHN_NO"),
                                        rs.getString("CUS_USERNAME"),
                                        rs.getString("CUS_PASSWORD"),
                                        rs.getString("CUS_EMAIL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers()throws ClassNotFoundException {
        String sql = "SELECT * FROM customer";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(rs.getInt("CUS_ID"),
                                           rs.getString("CUS_NAME"),
                                           rs.getString("CUS_PHN_NO"),
                                           rs.getString("CUS_USERNAME"),
                                           rs.getString("CUS_PASSWORD"),
                                           rs.getString("CUS_EMAIL")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
       
    
	    