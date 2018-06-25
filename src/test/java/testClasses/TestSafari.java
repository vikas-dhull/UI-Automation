package testClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class TestSafari {
	
	@Test
	public void safariTest() {
		WebDriver driver = new SafariDriver();
		driver.get("http://www.google.co.in");
		driver.quit();
	}

}
