package rtn26hcdrivers;

import org.testng.Assert;

public class DataDriver {
	public DataDriver() {};
	
	public void logLine(String message) {
		System.out.println(message);
	}
	
	public void message(String message) {
		System.out.println("\n" + message);
	}
	
	public void equals(Object actual, Object expected) {
		logLine("\nAssertion: checking if actual value [" +  actual + "] equals to expected value [" + expected + "]");
		Assert.assertEquals(actual, expected);
		logLine("Assertion result: Successful");
	}
	
	public void contains(String actual, String expected) {
		logLine("\nAssertion: checking if string found: " + expected);
		Assert.assertTrue(actual.contains(expected));
		logLine("Assertion result: Successful");
	}
	

    public double randomNumber(int maxValue) {
    	Double number = Math.random() * maxValue * 100;
    	return (double) Math.round(number)/100;
    }
	
	public String randomAlphabeticalString(int numberOfDigits) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<numberOfDigits; i++) {
            result.append(randomAlphabeticalLetter());
        }
        return result.toString();
	}

    private	static char letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
   
    private static char randomAlphabeticalLetter() {
        return letters[(int)Math.floor(Math.random() * 26)];
    }
}
