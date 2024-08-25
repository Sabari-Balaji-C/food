package util;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;

	public class PropertyUtil {
		public static Properties loadProperties(String fileName) {
	        Properties properties = new Properties();
	        try (FileInputStream input = new FileInputStream(fileName)) {
	            properties.load(input);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return properties;
	    }

	    public static String getPropertyString(Properties properties, String key) {
	        return properties.getProperty(key);
	    }

	}
