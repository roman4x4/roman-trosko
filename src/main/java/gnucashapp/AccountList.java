package gnucashapp;

import rtn26hcdrivers.RTn26Base;

public class AccountList extends RTn26Base{
	
	public static int getListSize() {
		return mobile.getListSize(AccountListMapping.list);
	}
	
	public static int getCardPositionByName(String accountName) {
		int listSize = getListSize();
		for (int index = 1; index <= listSize; index ++)
			if (mobile.getText(AccountListMapping.PrimaryText(index)).equalsIgnoreCase(accountName)) {
				return index;
			}
		return 0;			
	}
	
	public static boolean isAccountDisplayed(String accountName) {
		if (getCardPositionByName(accountName) > 0)
			return true;
		return false;
	}
	
	public static void tapAccountCard(String accountName) {
		tapAccountCard(getCardPositionByName(accountName));
	}
	
	public static void tapAccountCard(int CardPosition) {
		mobile.click(AccountListMapping.AccountCard(CardPosition));
		mobile.sleep(500);
	}
	
	public static String getSecondaryText(int CardPosition) {
		return mobile.getText(AccountListMapping.SecondaryText(CardPosition));
	}
	
	public static String getAccountBalance(int CardPosition) {
		return mobile.getText(AccountListMapping.AccountBalance(CardPosition));
	}
	
	public static void clickFavoriteStatus(int CardPosition) {
		mobile.click(AccountListMapping.FavoriteStatus(CardPosition));
	}
	
	public static void clickOptionsMenu(int CardPosition) {
		mobile.click(AccountListMapping.OptionsMenu(CardPosition));
	}
	
	public static void clickCreateTransactionIcon(int CardPosition) {
		mobile.click(AccountListMapping.CreateTransactionIcon(CardPosition));
	}
}
