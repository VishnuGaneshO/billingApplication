package testCases;

import org.testng.annotations.Test;
import pageElements.LoginPageElements;
import utilities.ExcelDataProvider;
import utilities.ReadConfigProperty;
import utilities.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;


@Listeners(utilities.TestListener.class)
public class Login extends WebDriverManager {
	public static WebDriver driver;
	LoginPageElements loginPageElements;
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();

	@Test(priority = 0, enabled = false, dataProviderClass=ExcelDataProvider.class, dataProvider = "invalidloginData")
	public void invalidLogin(String id, String password) {
		loginPageElements.login_With_Invalid_Credentials(id, password);
		Assert.assertEquals(loginPageElements.get_Invalid_Credentials_Text(), "These credentials do not match our records.");
	}

	@Test(priority = 1, enabled = true, dataProvider = "logins")
	public void login(String id, String password) {
		loginPageElements.login_With_Valid_Credentials(id, password);
		Assert.assertEquals(loginPageElements.get_Welcome_Text(), "Welcome admin,");
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
