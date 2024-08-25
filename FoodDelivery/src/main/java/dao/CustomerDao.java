package dao;
import entity.Customer;
import java.util.List;
import java.sql.SQLException;
public interface CustomerDao {
	void addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
    Customer authenticateCustomer(String username, String password) throws ClassNotFoundException, SQLException;
    Customer searchCustomerByUsername(String username) throws ClassNotFoundException, SQLException;
    Customer searchCustomerById(int customerId) throws ClassNotFoundException, SQLException;
    List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException;

}
