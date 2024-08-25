package util;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
public class DBPropertyUtil {
     public static String getConnectionString(String filename) {
    	 String connString=null;
    	 try {
    	 FileInputStream fis=new FileInputStream(filename);
    	 Properties props=new Properties();
    	 props.load(fis);
    	  connString=props.getProperty("protocol")+"//"+props.getProperty("host")+":"+ props.getProperty("port")+"/"+props.getProperty("database")
     	+"?user="+props.getProperty("user")+"&password="+props.getProperty("password");
    	  //System.out.println(connString);
    	 }catch(FileNotFoundException ex) {
    		 System.out.println("The file is not existed");
    		 ex.printStackTrace();
    	 }catch(IOException ex) {
    		 System.out.println("Error occured while loading file");
    		 ex.printStackTrace();
    	 }
    	 return connString;
    	 
     }
}
