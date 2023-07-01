package testCases;

import org.testng.annotations.Test;

import pageElements.LoginPageElements;
import pageElements.UserPageElements;
import utilities.ExcelReader;
import utilities.ReadConfigProperty;
import utilities.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UserManagement extends WebDriverManager {
	public static WebDriver driver;
	LoginPageElements loginPageElements;
	UserPageElements userPageElements;
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();
	ExcelReader excelReader = new ExcelReader();

	@Test(priority = 0, enabled = true, dataProvider = "logins")
	public void login(String id, String password) {
		loginPageElements.loginWithValidCredentials(id, password);
		Assert.assertEquals(loginPageElements.getWelcomeText(), "Welcome admin,");
	}

	@Test(priority = 1, enabled = true)
	public void user()  {
		userPageElements.addUser();
		userPageElements.userSearch(excelReader.getUserDetails(1, 5));
		Assert.assertEquals(userPageElements.getSearchResult(), excelReader.getUserDetails(1, 10));
	}
	
	@Test(priority = 2, enabled = true)
	public void edit() {
		userPageElements.editUser(excelReader.getUserDetails(1, 9));
		userPageElements.userSearch(excelReader.getUserDetails(1, 5));
		Assert.assertEquals(userPageElements.getSearchResult(), excelReader.getUserDetails(1, 8));
	}
	
	@Test(priority = 3, enabled = true)
	public void delete() {
		userPageElements.userSearch(excelReader.getUserDetails(1, 5));
		userPageElements.deleteUser();
		userPageElements.userSearch(excelReader.getUserDetails(1, 5));
		Assert.assertEquals(userPageElements.getNoRecordsFound(), "No matching records found");
	}
	

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		driver = launchBrowser(readConfigProperty.browser, readConfigProperty.url);
		loginPageElements = new LoginPageElements(driver);
		userPageElements = new UserPageElements(driver);
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
