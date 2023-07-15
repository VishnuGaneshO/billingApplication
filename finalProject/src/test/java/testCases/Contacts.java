package testCases;

import org.testng.annotations.Test;

import utilities.ExcelDataProvider;
import pageElements.ContactPageElements;
import pageElements.LoginPageElements;
import utilities.ExcelReader;
import utilities.ReadConfigProperty;
import utilities.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;


@Listeners(utilities.TestListener.class)
public class Contacts extends WebDriverManager {
	public static WebDriver driver;
	LoginPageElements loginPageElements;
	ContactPageElements contactPageElements;
	ExcelReader excelReader = new ExcelReader();
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();

	@Test(priority = 0, enabled = true, dataProvider = "logins")
	public void login(String id, String password) {
		loginPageElements.login_With_Valid_Credentials(id, password);
		Assert.assertEquals(loginPageElements.get_Welcome_Text(), "Welcome admin,");
	}

	@Test(priority = 1, enabled = true, dataProviderClass=ExcelDataProvider.class, dataProvider = "contactsData")
	public void suppliers(String name, String bussinessName, String payTrem, String mobile, String payTermMonth) throws InterruptedException {
		contactPageElements.add_suppliers(name, bussinessName, payTrem, mobile, payTermMonth);
		contactPageElements.search(name);
		Assert.assertEquals(contactPageElements.get_firstRowName(),name);
	}
	
	
	
	@Parameters("browser")
	@BeforeTest(alwaysRun = true)
	public void beforeTest(String browser) {
		driver = launchBrowser(browser, readConfigProperty.url);
		loginPageElements = new LoginPageElements(driver);
		contactPageElements = new ContactPageElements(driver);
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
