package testCases;

import org.testng.annotations.Test;

import pageElements.LoginPageElements;
import pageElements.UserPageElements;
import utilities.ExcelDataProvider;
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

	@Test(priority = 0, enabled = true, dataProvider = "logins" ,groups="functional")
	public void login(String id, String password) {
		loginPageElements.login_With_Valid_Credentials(id, password);
		Assert.assertEquals(loginPageElements.get_Welcome_Text(), "Welcome admin,");
	}

	@Test(priority = 1, enabled = true, dataProviderClass = ExcelDataProvider.class, dataProvider = "userData", groups="functional")
	public void user(String prefix, String firstName, String lastname, String email, String roleInput, String username,
			String password, String newName, String newLastName, String role) 
	{
		userPageElements.add_User(prefix, firstName, lastname, email, roleInput, username,password, newName, newLastName, role );
		userPageElements.user_Search(username);
		Assert.assertEquals(userPageElements.get_firstRowUsername(), username);
	}

	@Test(priority = 2, enabled = true, dataProviderClass = ExcelDataProvider.class, dataProvider = "userData")
	public void edit(String prefix, String firstName, String lastname, String email, String roleInput, String username,
			String password, String newName, String newLastName, String role ) 
	{
		userPageElements.edit_User(newLastName);
		userPageElements.user_Search(username);
		Assert.assertEquals(userPageElements.get_firstRowName(), newName);
	}

	@Test(priority = 3, enabled = true, dataProviderClass = ExcelDataProvider.class, dataProvider = "userData")
	public void delete(String prefix, String firstName, String lastname, String email, String roleInput, String username,
			String password, String newName, String newLastName, String role ) throws InterruptedException
	{
		userPageElements.user_Search(username);
		userPageElements.delete_User();
		userPageElements.user_Search(username);
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
