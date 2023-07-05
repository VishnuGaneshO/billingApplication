package testCases;

import org.testng.annotations.Test;

import pageElements.LoginPageElements;
import pageElements.UserPageElements;
import utilities.ExcelReader;
import utilities.ReadConfigProperty;
import utilities.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

@Listeners(utilities.TestListener.class)
public class UserManagement extends WebDriverManager {
	public static WebDriver driver;
	LoginPageElements loginPageElements;
	UserPageElements userPageElements;
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();
	ExcelReader excelReader = new ExcelReader();

	@Test(priority = 0, enabled = true, dataProvider = "logins")
	public void login(String id, String password) {
		loginPageElements.login_With_Valid_Credentials(id, password);
		Assert.assertEquals(loginPageElements.get_Welcome_Text(), "Welcome admin,");
	}

	@Test(priority = 1, enabled = true)
	public void user()  {
		userPageElements.add_User();
		userPageElements.user_Search(excelReader.getUserDetails(1, 5));
		Assert.assertEquals(userPageElements.get_Search_Result(), excelReader.getUserDetails(1, 10));
	}
	
	@Test(priority = 2, enabled = true)
	public void edit() {
		userPageElements.edit_User(excelReader.getUserDetails(1, 9));
		userPageElements.user_Search(excelReader.getUserDetails(1, 5));
		Assert.assertEquals(userPageElements.get_Search_Result(), excelReader.getUserDetails(1, 8));
	}
	
	@Test(priority = 3, enabled = true)
	public void delete() {
		userPageElements.user_Search(excelReader.getUserDetails(1, 5));
		userPageElements.delete_User();
		userPageElements.user_Search(excelReader.getUserDetails(1, 5));
		Assert.assertEquals(userPageElements.get_No_Records_Found(), "No matching records found");
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
