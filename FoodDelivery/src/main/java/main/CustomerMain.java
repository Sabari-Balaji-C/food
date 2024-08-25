package main;
import java.sql.SQLException;
import entity.Customer;
import java.util.Scanner;
import dao.CustomerDao;
import dao.	CustomerDaoImpl;
import java.util.Scanner;
import dao.CustomerDao;
public class CustomerMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerDao dao = new CustomerDaoImpl();
        int choice;
        do {

        System.out.println("Choose an operation: ");
        System.out.println("1. Add Customer");
        System.out.println("2. Authenticate Customer");
        System.out.println("3. Search Customer by Username");
        System.out.println("4. Search Customer by ID");
         choice = sc.nextInt();
        sc.nextLine(); // Consume the newline

        try {
        	
            switch (choice) {
                case 1:
                    System.out.println("Enter Customer Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Phone Number: ");
                    String phone = sc.nextLine();
                    System.out.println("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.println("Enter Password: ");
                    String password = sc.nextLine();
                    System.out.println("Enter Email: ");
                    String email = sc.nextLine();

                    Customer customer = new Customer(0, name, phone, username, password, email);
                    dao.addCustomer(customer);
                    System.out.println("New customer added: " + customer.getCusName());
                    break;

                case 2:
                    System.out.println("Enter Username: ");
                    String uname = sc.nextLine();
                    System.out.println("Enter Password: ");
                    String pass = sc.nextLine();

                    Customer authenticatedCustomer = dao.authenticateCustomer(uname, pass);
                    if (authenticatedCustomer != null) {
                        System.out.println("Customer authenticated: " + authenticatedCustomer.getCusName());
                    } else {
                        System.out.println("Customer authentication failed.");
                    }
                    break;

                case 3:
                    System.out.println("Enter Username to search: ");
                    String searchUsername = sc.nextLine();

                    Customer searchedCustomer = dao.searchCustomerByUsername(searchUsername);
                    if (searchedCustomer != null) {
                        System.out.println("Customer found by username: " + searchedCustomer.getCusName());
                    } else {
                        System.out.println("Customer not found by username.");
                    }
                    break;

                case 4:
                    System.out.println("Enter Customer ID to search: ");
                    int customerId = sc.nextInt();

                    Customer customerById = dao.searchCustomerById(customerId);
                    if (customerById != null) {
                        System.out.println("Customer found by ID: " + customerById.getCusName());
                    } else {
                        System.out.println("Customer not found by ID.");
                    }
                    break;
                    
               case 5:
                	System.out.println("exit");
                	break;
                   
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally block...");
        }
    }while (choice<5 && choice >0);
}
}

