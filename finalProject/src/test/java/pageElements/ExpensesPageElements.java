package pageElements;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonActions;
import utilities.ExcelReader;
import utilities.FileUpload;
import utilities.JavscriptExecuters;
import utilities.ReadConfigProperty;
import utilities.SelectClass;
import utilities.WaitConditions;

public class ExpensesPageElements extends CommonActions {
	WebDriver driver;
	WaitConditions waitConditions= new WaitConditions();
	ExcelReader excelReader= new ExcelReader();
	SelectClass selectClass = new SelectClass();
	FileUpload fileUpload = new FileUpload();
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();
	JavscriptExecuters javscriptExecuters = new JavscriptExecuters();
	
	public ExpensesPageElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath="//i[@class='fa fa-minus-circle']//parent::a[1]")
	public WebElement expenses;
	
	@FindBy(xpath="//a[@href='https://qalegend.com/billing/public/expenses']")
	public WebElement listExpenses;
	
	@FindBy(xpath="//a[contains(@class,'btn btn-block')]")
	public WebElement add;
	
	@FindBy(id="select2-location_id-container")
	public WebElement bussinessLocation;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	public WebElement bussinessLocationInput;
	
	@FindBy(xpath="//ul[@id='select2-location_id-results']//child::li")
	public List<WebElement> bussinessLocationList;
	
	@FindBy(id="select2-expense_category_id-container")
	public WebElement expenseCategory;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	public WebElement expenseCategoryInput;
	
	@FindBy(xpath="//ul[@id='select2-expense_category_id-results']//child::li")
	public List<WebElement> expenseCategoryList;
	
	@FindBy(id="ref_no")
	public WebElement referenceNo;
	
	@FindBy(id="final_total")
	public WebElement totalAmount;
	
	@FindBy(id="select2-expense_for-container")
	public WebElement expenseFor;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	public WebElement expenseForInput;
	
	@FindBy(xpath="//ul[@id='select2-expense_for-results']//child::li")
	public List<WebElement> expenseForList;
	
	@FindBy(id="upload_document")
	public WebElement browse;
	
	@FindBy(id="additional_notes")
	public WebElement expenseNote;
	
	@FindBy(xpath="//button[@class='btn btn-primary pull-right']")
	public WebElement save;
	
	@FindBy(xpath="//input[@class='form-control input-sm']")
	public WebElement search;
	
	@FindBy(xpath="//button[@class='btn btn-info dropdown-toggle btn-xs']")
	public WebElement actions;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']/li[2]/a")
	public WebElement downloadDocument;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']/li[3]/a")
	public WebElement delete;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']/li[5]/a")
	public WebElement addPayment;
	
	@FindBy(xpath="//button[@class='swal-button swal-button--confirm swal-button--danger']")
	public WebElement ok;
	
	@FindBy(xpath="//table[@id='expense_table']/tbody/tr/td[2]")
	public WebElement firstRowReferenceNo;
	
	@FindBy(xpath="//td[@class='dataTables_empty']")
	public WebElement noDataFound;
	
	@FindBy(id="method")
	public WebElement payVia;
	
	@FindBy(id="account_id")
	public WebElement paymentAccount;
	
	@FindBy(id="note")
	public WebElement paymentNote;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	public WebElement savePayment;
	
	@FindBy(xpath="//a[@class='view_payment_modal payment-status no-print']/span")
	public WebElement paymentStatus;

	@FindBy(id="document")
	public WebElement chooseFile;
	
	
	public void add_Expenses()  {
		click(expenses);
		click(listExpenses);
		click(add);
		click(bussinessLocation);
		sendKeys(bussinessLocationInput, excelReader.getExpenseDetails(1, 0));
		selectClass.behavioralDropDown(bussinessLocationList, excelReader.getExpenseDetails(2, 0));
		click(expenseCategory);
		sendKeys(expenseCategoryInput, excelReader.getExpenseDetails(1, 1));
		selectClass.behavioralDropDown(expenseCategoryList, excelReader.getExpenseDetails(2, 1));
		sendKeys(referenceNo,excelReader.getExpenseDetails(1, 2));
		sendKeys(totalAmount, excelReader.getExpenseDetails(1, 3));
		click(expenseFor);
		sendKeys(expenseForInput, excelReader.getExpenseDetails(1, 4));
		selectClass.behavioralDropDown(expenseForList, excelReader.getExpenseDetails(2, 4));
		javscriptExecuters.clickJsElement(browse, driver);
		fileUpload.upload(readConfigProperty.fileLocation);
		sendKeys(expenseNote, excelReader.getExpenseDetails(1, 5));
		click(save);
		
	}
	
	public String get_firstRowReferenceNo() {
		waitConditions.explicitWait_elementvisibility(driver, firstRowReferenceNo, 5);
		String text= firstRowReferenceNo.getText();
		return text;
	}
	
	public void download() {
		waitConditions.explicitWait_elementvisibility(driver, actions, 5);
		click(actions);
		waitConditions.explicitWait_elementclickable(driver, downloadDocument, 5);
		click(downloadDocument);
		
	}
	
	public void expense_Delete() {
		waitConditions.explicitWait_elementvisibility(driver, actions, 5);
		click(actions);
		click(delete);
		waitConditions.explicitWait_elementvisibility(driver, ok, 5);
		click(ok);
	}
	
	public void add_Payment() throws InterruptedException {
		waitConditions.explicitWait_elementvisibility(driver, actions, 5);
		click(actions);
		click(addPayment);
		waitConditions.explicitWait_elementvisibility(driver, payVia, 5);
		selectClass.dropdown(payVia, excelReader.getExpenseDetails(1, 6));
		selectClass.dropdown(paymentAccount,excelReader.getExpenseDetails(1, 7));
//		click(chooseFile);
		javscriptExecuters.clickJsElement(chooseFile, driver);
		fileUpload.upload(readConfigProperty.paymentFile);
		sendKeys(paymentNote, excelReader.getExpenseDetails(1, 8));
		click(savePayment);
		
	}
	
	public String get_Payment_Status() {
		waitConditions.explicitWait_elementvisibility(driver, paymentStatus, 5);
		String text=paymentStatus.getText();
		return text;
	}
	
	public String get_noData_Found() {
		waitConditions.explicitWait_elementvisibility(driver, noDataFound, 5);
		String text=noDataFound.getText();
		return text;
	}
	
	
	
}
