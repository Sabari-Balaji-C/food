package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnUtil {
	public static Connection getConnection() {
		String connString = null;
		try {
			connString = DBPropertyUtil.getConnectionString("db.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=null;
		try 
		{
			if(connString!=null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/cmstest","root","root");
			}
			else {
				System.err.println("Connection string is null");
				//throw nullreference exception
			}
        } catch (ClassNotFoundException e) {
        	System.err.println("ERROR:Driver is not loaded..");
            e.printStackTrace();
        } catch (SQLException e) {
        	System.err.println("ERROR:Connection is not established..");
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}

