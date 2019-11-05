package gnucashapp;

import rtn26hcdrivers.ObjectMap;
import rtn26hcdrivers.ObjectMap.LocatorType;
import rtn26hcdrivers.RTn26Base;

public class AccountListMapping extends RTn26Base{
	private static final String primary_text = "//*[@resource-id='org.gnucash.android:id/primary_text']";
	private static final String secondary_text = "//*[@resource-id='org.gnucash.android:id/secondary_text']";
	private static final String account_balance = "//*[@resource-id='org.gnucash.android:id/account_balance']";
	private static final String create_transaction = "//*[@resource-id='org.gnucash.android:id/create_transaction']";
	private static final String favorite_status = "//*[@resource-id='org.gnucash.android:id/favorite_status']";
	private static final String options_menu = "//*[@resource-id='org.gnucash.android:id/options_menu']";
	private static final String baseXPath = "//*[@resource-id='org.gnucash.android:id/account_recycler_view']/android.widget.FrameLayout";

	public static final ObjectMap list = new ObjectMap("Accounts list", LocatorType.XPATH, baseXPath);

	private static String getAccountCard(int index) {
		return baseXPath + "[" + index + "]";
	}
	
	public static ObjectMap AccountCard(int CardPosition) {
		return new ObjectMap("Account Card " + CardPosition, LocatorType.XPATH, getAccountCard(CardPosition));
	}
	
	public static ObjectMap PrimaryText(int CardPosition) {
		return new ObjectMap("Account Card " + CardPosition + " secondary text", LocatorType.XPATH, getAccountCard(CardPosition) + primary_text);
	}
	
	public static ObjectMap SecondaryText(int CardPosition) {
		return new ObjectMap("Account Card " + CardPosition + " secondary text", LocatorType.XPATH, getAccountCard(CardPosition) + secondary_text);
	}
	
	public static ObjectMap AccountBalance(int CardPosition) {
		return new ObjectMap("Account Card " + CardPosition + " account balance", LocatorType.XPATH, getAccountCard(CardPosition) + account_balance);
	}
	
	public static ObjectMap FavoriteStatus(int CardPosition) {
		return new ObjectMap("Account Card " + CardPosition + " favorite icon", LocatorType.XPATH, getAccountCard(CardPosition) + favorite_status);
	}
	
	public static ObjectMap OptionsMenu(int CardPosition) {
		return new ObjectMap("Account Card " + CardPosition + " options icon", LocatorType.XPATH, getAccountCard(CardPosition) + options_menu);
	}
	
	public static ObjectMap CreateTransactionIcon(int CardPosition) {
		return new ObjectMap("Account Card " + CardPosition + " create transaction icon", LocatorType.XPATH, getAccountCard(CardPosition) + create_transaction);
	}
}
