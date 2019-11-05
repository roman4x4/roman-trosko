package rtn26hcdrivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class RTn26Base {
	public static HttpDriver http = new HttpDriver();
	public static DataDriver data = new DataDriver();
	public static MobileDriver mobile = new MobileDriver();
	public static String bbApiBaseUrl;
	public static String udidAndroidDevice;
	public static String platformVersionAndroidDevice;
	public static String appiumHubUrl;
	public static String appPackage;
	public static String appActivity;
	
	@Parameters("environment")
	@BeforeSuite (alwaysRun = true)
	public void loadEnvironmentProperties(@Optional("default") String environment) {
		try {
			File file = new File("src/test/resources/environment/" + environment + ".properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			
			bbApiBaseUrl = properties.getProperty("bbApiBaseUrl");
			udidAndroidDevice = properties.getProperty("udidAndroidDevice");
			appiumHubUrl = properties.getProperty("appiumHubUrl");
			appPackage = properties.getProperty("appPackage");
			appActivity = properties.getProperty("appActivity");
		} catch (IOException e) {
			e.printStackTrace();
			}		
	}
}
