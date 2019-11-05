package rtn26hcdrivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MobileDriver{
	private DesiredCapabilities caps;
	private AppiumDriver<MobileElement> driver = null;
	private String hubURL;
	private DataDriver data;
	
	public MobileDriver() {
		caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "MyPhone");
		caps.setCapability("noReset", "true");
		data = new DataDriver();
	};
	
	public void setCapabilities(String capabilityName, String value) {
		this.caps.setCapability(capabilityName, value);
	}

	public void setHubUrl(String hubURL) {
		this.hubURL = hubURL;
	}
	
	public void openApp() {
		try {
			driver = new AndroidDriver<MobileElement>(new URL(hubURL), caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void click(ObjectMap object) {
		data.logLine("Clicking element: " + object.name);
		data.logLine("Locator: " + object.locator);
		data.logLine("\n");
		getMobileElement(object).click();
	}
	
	public void typeText(ObjectMap object, String text) {
		data.logLine("Typing text [" + text + "] into element: " + object.name);
		data.logLine("Locator: " + object.locator);
		data.logLine("\n");
		getMobileElement(object).sendKeys(text);
	}
	
	public String getText(ObjectMap object) {
		data.logLine("Getting text of element: " + object.name);
		data.logLine("Locator: " + object.locator);
		String text = getMobileElement(object).getText();
		data.logLine("Text found: " + text);
		data.logLine("\n");
		return text;
	}
	
	public String getAttribute(ObjectMap object, String attributeName) {
		data.logLine("Getting attribute of element: " + object.name);
		data.logLine("Locator: " + object.locator);
		String atributeValue = getMobileElement(object).getAttribute(attributeName);
		data.logLine("Attribute found: " + atributeValue);
		data.logLine("\n");
		return atributeValue;
	}
	
	private MobileElement getMobileElement(ObjectMap object) {
		MobileElement mobileElement;
		switch (object.locatorType) {
		case XPATH:
			mobileElement = driver.findElementByXPath(object.locator);
			break;
		case ID:
			mobileElement = driver.findElementById(object.locator);
			break;
		default: 
			mobileElement = null; 
			break;
		}
		return mobileElement;
	}
	
	public int getListSize(ObjectMap object) {
		int size = 0;
		switch (object.locatorType) {
		case XPATH:
			size = driver.findElementsByXPath(object.locator).size();
			break;
		case ID:
			size = driver.findElementsById(object.locator).size();
			break;
		default: 
			break;
		}
		return size;
	}

	public void closeApp() {
		driver.closeApp();
	}
	
	public void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
