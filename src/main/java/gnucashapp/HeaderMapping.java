package gnucashapp;

import rtn26hcdrivers.ObjectMap;
import rtn26hcdrivers.ObjectMap.LocatorType;

public class HeaderMapping {
	public static ObjectMap latestTab = new ObjectMap("Tab KÜRLZLICH", LocatorType.XPATH, "//android.support.v7.app.ActionBar.Tab[1]");
	public static ObjectMap allTab = new ObjectMap("Tab ALL", LocatorType.XPATH, "//android.support.v7.app.ActionBar.Tab[2]");
	public static ObjectMap favoritesTab = new ObjectMap("Tab FAVORITEN", LocatorType.XPATH, "//android.support.v7.app.ActionBar.Tab[3]");
	
	public static ObjectMap openMenuIcon = new ObjectMap("Open Menu Icon in header", LocatorType.XPATH, "//android.widget.ImageButton[@content-desc='Navigationsleiste geöffnet']");
	public static ObjectMap headerText = new ObjectMap("Header text", LocatorType.XPATH, "//android.view.ViewGroup[@resource-id='org.gnucash.android:id/toolbar']/android.widget.TextView");
	public static ObjectMap closeMenuIcon = new ObjectMap("Close Menu Icon in header", LocatorType.XPATH, "//android.widget.ImageButton[@content-desc='Navigationsleiste geschlossen']");
	public static ObjectMap searchIcon = new ObjectMap("Search Icon in header", LocatorType.XPATH, "//android.widget.TextView[@content-desc='Suchen']");
	public static ObjectMap searchInput = new ObjectMap("Search Input in header", LocatorType.ID, "search_src_text");
	public static ObjectMap backButton = new ObjectMap("Back Button in header", LocatorType.XPATH, "//android.widget.ImageButton[@content-desc='Minimieren']");
}
