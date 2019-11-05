package rtn26task2;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import gnucashapp.AccountList;
import gnucashapp.Header;
import rtn26hcdrivers.RTn26Base;

public class MobileTests extends RTn26Base{
	
	@BeforeTest (alwaysRun = true)
	private void setDesiredCapabilities() {
		mobile.setCapabilities("udid", udidAndroidDevice);
		mobile.setCapabilities("appPackage", appPackage);
		mobile.setCapabilities("appActivity", appActivity);
		mobile.setHubUrl(appiumHubUrl);
		mobile.openApp();
	}
	
	@AfterTest (alwaysRun = true)
	private void closeApp() {
		mobile.closeApp();
	}
	
	@Test
	private void testFilteringAccountList() {
		Header.checkHeaderText("Konten");
		Header.tapAllTab();		
		
		data.message("Check 5 accounts displayed");
		data.equals(AccountList.getListSize(), 5);
		
		Header.searchList("ab");
		data.message("Check 3 accounts displayed");
		data.equals(AccountList.getListSize(), 3);
		data.message("Check account names displayed");
		data.equals(AccountList.isAccountDisplayed("Abfall"));
		data.equals(AccountList.isAccountDisplayed("Abonnements"));
		data.equals(AccountList.isAccountDisplayed("Sozialabgaben"));
		
		data.message("Check Erträge and Fremdkapital accounts not displayed");
		data.equals(!AccountList.isAccountDisplayed("Erträge"));
		data.equals(!AccountList.isAccountDisplayed("Fremdkapital"));
		
		Header.tabBackButton();
		
		data.equals(AccountList.getListSize(), 5);
		data.message("Check Erträge and Fremdkapital accounts displayed");
		data.equals(AccountList.isAccountDisplayed("Erträge"));
		data.equals(AccountList.isAccountDisplayed("Fremdkapital"));
	}
	
	@Test
	private void testClickingCards() {
		Header.tapAllTab();	
		data.message("Check Aufwendungen elements");
		int cardPosition = AccountList.getCardPositionByName("Aufwendungen");
		data.equals(AccountList.getSecondaryText(cardPosition), "15 Unterkonten");
		
		AccountList.tapAccountCard(cardPosition);
		AccountList.tapAccountCard("Computer");
	}
}
