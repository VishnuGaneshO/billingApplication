package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadConfigProperty {
	String path = System.getProperty("user.dir");
	public String url;
	public String browser;
	public String fileLocation;
	public String paymentFile;
	
	public static void main(String args[]) {
		ReadConfigProperty objprop = new ReadConfigProperty();
		System.out.println(objprop.fileLocation);
	}

	public ReadConfigProperty() {
		Properties objProperties = new Properties();
		InputStream objInput = null;

		try {

			objInput = new FileInputStream(path + "//src//main//resources//properties//config.properties");

			// load a properties file
			objProperties.load(objInput);

			// get the property value and print it out
			url = objProperties.getProperty("url");
			browser = objProperties.getProperty("browser");
			fileLocation= objProperties.getProperty("fileLocation");
			paymentFile=objProperties.getProperty("paymentFile");
			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (objInput != null) {
				try {
					objInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
