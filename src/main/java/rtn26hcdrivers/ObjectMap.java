package rtn26hcdrivers;

public class ObjectMap {
	public enum LocatorType {XPATH, ID};
	
	public String name;
	public String locator;
	public LocatorType locatorType;
	
	public ObjectMap(String name, LocatorType locatorType, String locator) {
		this.name = name;
		this.locatorType = locatorType;
		this.locator = locator;
	}
}
