package testCases;

import org.testng.annotations.Test;

import pageElements.ContactPageElements;
import pageElements.LoginPageElements;
import utilities.ExcelReader;
import utilities.ReadConfigProperty;
import utilities.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

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

	@Test(priority = 1, enabled = true)
	public void suppliers() throws InterruptedException {
		contactPageElements.add_suppliers();
		contactPageElements.search(excelReader.getContactDetails(1, 0));
		Assert.assertEquals(contactPageElements.get_firstRowName(), excelReader.getContactDetails(1, 0));
	}
	
	@Test(priority = 2, enabled = false)
	public void doPayment() throws InterruptedException {
		contactPageElements.search(excelReader.getContactDetails(1, 0));
		contactPageElements.pay_dueAmount();
	}
	
	@Test(priority = 3, enabled = false)
	public void deleteSuppliers() throws InterruptedException {
		contactPageElements.search(excelReader.getContactDetails(1, 0));
		contactPageElements.delete_suppliers();
		contactPageElements.search(excelReader.getContactDetails(1, 0));
		Assert.assertEquals(contactPageElements.get_noDataFound(), "No matching records found");
	}
	

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		driver = launchBrowser(readConfigProperty.browser, readConfigProperty.url);
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
