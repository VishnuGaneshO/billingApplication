package testCases;

import org.testng.annotations.Test;
import pageElements.LoginPageElements;
import utilities.ReadConfigProperty;
import utilities.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LoginPage extends WebDriverManager {
	public static WebDriver driver;
	LoginPageElements loginPageElements;
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();

	@Test(priority = 0, enabled = true)
	public void invalidLogin() {
		loginPageElements.loginWithInvalidCredentials("admin", "12345");
		Assert.assertEquals(loginPageElements.getInvalidCredentialsText(), "These credentials do not match our records.");
	}

	@Test(priority = 1, enabled = true, dataProvider = "logins")
	public void login(String id, String password) {
		loginPageElements.loginWithValidCredentials(id, password);
		Assert.assertEquals(loginPageElements.getWelcomeText(), "Welcome admin,");
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		driver = launchBrowser(readConfigProperty.browser, readConfigProperty.url);
		loginPageElements = new LoginPageElements(driver);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		driver.close();
	}

	@DataProvider(name = "logins")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "admin", "123123" }

		};

	}

}
