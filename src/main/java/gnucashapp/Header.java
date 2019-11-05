package gnucashapp;

import rtn26hcdrivers.RTn26Base;

public class Header extends RTn26Base{
	
	public static void checkHeaderText(String text) {
		data.message("Checking header text"); 
		data.equals(mobile.getText(HeaderMapping.headerText), text);
	}
	
	public static void tapAllTab() {
		data.message("Tapping ALLE Tab"); 
		mobile.click(HeaderMapping.allTab);
	}
	
	public static void searchList(String searchString) {
		mobile.click(HeaderMapping.searchIcon);
		mobile.typeText(HeaderMapping.searchInput, searchString);
		mobile.sleep(500);
	}
	
	public static void tabBackButton() {
		mobile.click(HeaderMapping.backButton);
		mobile.sleep(500);
	}
}