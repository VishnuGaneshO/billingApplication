package testCases;

import org.testng.annotations.Test;

import pageElements.ExpensesPageElements;
import pageElements.LoginPageElements;
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
public class Expenses extends WebDriverManager {
	public static WebDriver driver;
	LoginPageElements loginPageElements;
	ExpensesPageElements expensesPageElements;
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();
	ExcelReader excelReader = new ExcelReader();

	@Test(priority = 0, enabled = true, dataProvider = "logins")
	public void login(String id, String password) {
		loginPageElements.login_With_Valid_Credentials(id, password);
		Assert.assertEquals(loginPageElements.get_Welcome_Text(), "Welcome admin,");
	}

	@Test(priority = 1, enabled = true)
	public void addExpense()  {
		expensesPageElements.add_Expenses();
		Assert.assertEquals(expensesPageElements.get_firstRowReferenceNo(), excelReader.getExpenseDetails(1, 2));
	}
	

	@Test(priority = 2, enabled = true)
	public void doPayment() throws InterruptedException  {
		expensesPageElements.add_Payment();
		Assert.assertEquals(expensesPageElements.get_Payment_Status(), excelReader.getExpenseDetails(1, 9));
	}
	
	@Test(priority = 3, enabled = true)
	public void downloadExpenses() throws InterruptedException {
		expensesPageElements.download();
	}
	
	
	@Test(priority = 4, enabled = true)
	public void deleteExpenses() {
		expensesPageElements.expense_Delete();
		Assert.assertEquals(expensesPageElements.get_noData_Found(), excelReader.getExpenseDetails(1, 10));
	}
	
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		driver = launchBrowser(readConfigProperty.browser, readConfigProperty.url);
		loginPageElements = new LoginPageElements(driver);
		expensesPageElements = new ExpensesPageElements(driver);
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
