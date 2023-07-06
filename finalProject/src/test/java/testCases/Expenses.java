package testCases;

import org.testng.annotations.Test;

import pageElements.ExpensesPageElements;
import pageElements.LoginPageElements;
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

	@Test(priority = 1, enabled = true, dataProviderClass = ExcelDataProvider.class, dataProvider = "expenseData")
	public void addExpense(String businessLocation, String expenseCategory, String referenceNo, String totalAmount,
			String expenseFor, String expenseNote, String payment, String paymentAccount, String paymentnote,
			String paymentStatus) 
	{
		expensesPageElements.add_Expenses(businessLocation, expenseCategory, referenceNo, totalAmount, expenseFor, expenseNote );
		Assert.assertEquals(expensesPageElements.get_firstRowReferenceNo(), referenceNo);
	}

	@Test(priority = 2, enabled = true, dataProviderClass = ExcelDataProvider.class, dataProvider = "expenseData")
	public void doPayment(String businessLocation, String expenseCategory, String referenceNo, String totalAmount,
			String expenseFor, String expenseNote, String payment, String paymentAccount, String paymentnote,
			String paymentStatus) throws InterruptedException 
	{
		expensesPageElements.add_Payment(payment, paymentAccount, paymentnote );
		Assert.assertEquals(expensesPageElements.get_Payment_Status(), paymentStatus);
	}

	@Test(priority = 3, enabled = true)
	public void downloadExpenses() throws InterruptedException {
		expensesPageElements.download();
	}

	@Test(priority = 4, enabled = true)
	public void deleteExpenses() {
		expensesPageElements.expense_Delete();
		Assert.assertEquals(expensesPageElements.get_noData_Found(), "No data available in table");
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
