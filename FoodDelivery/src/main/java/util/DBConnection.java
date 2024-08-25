package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.io.InputStream;

public class DBConnection {
	private static Connection conn;


		public static Connection getConnection() throws ClassNotFoundException, SQLException {
			String usernameDb = "root";
			String passwordDb = "root";
			String urlDb = "jdbc:mysql://localhost:3306/cmstest?useSSL=false";
			String driverName = "com.mysql.cj.jdbc.Driver";

			try {
				Class.forName(driverName);
				System.out.println("Driver loaded successfully..");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver could not be loaded");
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
			System.out.println("Connection established..");
			} catch (SQLException e) {
				System.out.println("Connection could not be established");
				e.printStackTrace();
			}

			return conn;
		}

		public static void dbClose(Connection conn) {
			try {
				conn.close();
			System.out.println("Connection closed..");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
 
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
	        // Get a connection to the database
	        Connection conn = DBConnection.getConnection();

	        // If connection is successful, perform database operations here
	        // Example: you could create a statement, execute a query, etc.

	        // Close the connection
	        if (conn != null) {
	        	DBConnection.dbClose(conn);
	        }
	    }
	}

		 
