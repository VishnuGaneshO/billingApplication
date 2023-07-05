package pageElements;

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

public class ContactPageElements extends CommonActions {
	WebDriver driver;
	WaitConditions waitConditions= new WaitConditions();
	ExcelReader excelReader= new ExcelReader();
	SelectClass selectClass = new SelectClass();
	FileUpload fileUpload = new FileUpload();
	ReadConfigProperty readConfigProperty = new ReadConfigProperty();
	JavscriptExecuters javscriptExecuters = new JavscriptExecuters();
	
	public ContactPageElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(id="tour_step4_menu")
	public WebElement contacts;
	
	@FindBy(xpath="//a[@href='https://qalegend.com/billing/public/contacts?type=supplier']")
	public WebElement suppliers;
	
	@FindBy(xpath="//button[@class='btn btn-block btn-primary btn-modal']")
	public WebElement add;
	
	@FindBy(id="contact_type")
	public WebElement contactType;
	
	@FindBy(id="name")
	public WebElement name;
	
	@FindBy(id="supplier_business_name")
	public WebElement businessType;
	
	@FindBy(id="contact_id")
	public WebElement contactID;
	
	@FindBy(id="tax_number")
	public WebElement taxNumber;
	
	@FindBy(id="opening_balance")
	public WebElement openingBalance;
	
	@FindBy(id="pay_term_number")
	public WebElement payTerm;
	
	@FindBy(xpath="//select[@class='form-control width-60 pull-left']")
	public WebElement payTermSelect;
	
	@FindBy(id="email")
	public WebElement email;
	
	@FindBy(id="mobile")
	public WebElement mobile;
	
	@FindBy(id="alternate_number")
	public WebElement alternateNumber;
	
	@FindBy(id="landline")
	public WebElement landline;
	
	@FindBy(id="city")
	public WebElement city;
	
	@FindBy(id="state")
	public WebElement state;
	
	@FindBy(id="country")
	public WebElement country;
	
	@FindBy(id="landmark")
	public WebElement landmark;
	
	@FindBy(id="custom_field1")
	public WebElement customField1;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	public WebElement save;
	
	@FindBy(xpath="//input[@class='form-control input-sm']")
	public WebElement search;
	
	@FindBy(xpath="//table[@id='contact_table']/tbody/tr/td[3]")
	public WebElement firstRowName;
	
	@FindBy(xpath="//button[@class='btn btn-info dropdown-toggle btn-xs']")
	public WebElement actions;
	
	@FindBy(xpath="//a[@class='delete_contact_button']")
	public WebElement delete;
	
	@FindBy(xpath="//button[@class='swal-button swal-button--confirm swal-button--danger']")
	public WebElement ok;
	
	@FindBy(xpath="//td[@class='dataTables_empty']")
	public WebElement noDataFound;
	
	@FindBy(xpath="//a[@class='pay_purchase_due']")
	public WebElement payDueAmount;
	
	@FindBy(id="method")
	public WebElement payVia;
	
	@FindBy(id="document")
	public WebElement chooseFile;
	
	@FindBy(id="account_id")
	public WebElement paymentAccount;
	
	@FindBy(id="note")
	public WebElement paymentNote;
	
	@FindBy(xpath="(//button[@class='btn btn-primary'])[2]")
	public WebElement savePayment;
	
	public void add_suppliers() {
		click(contacts);
		click(suppliers);
		waitConditions.explicitWait_elementvisibility(driver, add, 5);
		click(add);
//		waitConditions.explicitWait_elementvisibility(driver, contactType, 5);
//		selectClass.dropdown(contactType, excelReader.getContactDetails(4, 0));
		sendKeys(name, excelReader.getContactDetails(1, 0));
		sendKeys(businessType, excelReader.getContactDetails(1, 1));
		sendKeys(contactID, excelReader.getContactDetails(1, 2));
		sendKeys(taxNumber, excelReader.getContactDetails(1, 3));
		sendKeys(openingBalance, excelReader.getContactDetails(1, 4));
		sendKeys(payTerm, excelReader.getContactDetails(1, 5));
		selectClass.dropdown(payTermSelect, excelReader.getContactDetails(4, 1));
		sendKeys(email, excelReader.getContactDetails(1, 6));
		sendKeys(mobile, excelReader.getContactDetails(1, 7));
		sendKeys(alternateNumber, excelReader.getContactDetails(1, 8));
		sendKeys(landline, excelReader.getContactDetails(1, 9));
		sendKeys(city, excelReader.getContactDetails(1, 10));
		sendKeys(state, excelReader.getContactDetails(1, 11));
		sendKeys(country, excelReader.getContactDetails(1, 12));
		sendKeys(landmark, excelReader.getContactDetails(1, 13));
		sendKeys(customField1, excelReader.getContactDetails(1, 14));
		click(save);
		
	}
	
	public void search(String key) {
//		Thread.sleep(3000);
		waitConditions.explicitWait_elementvisibility(driver, search, 5);
		clear(search);
		click(search);
		sendKeys(search, key);
		
	}
	
	public void delete_suppliers() {
		waitConditions.explicitWait_elementclickable(driver, actions, 5);
		click(actions);
		click(delete);
		waitConditions.explicitWait_elementvisibility(driver, ok, 5);
		click(ok);
	}
	
	public String get_noDataFound() {
		String text= noDataFound.getText();
		return text;
		
	}
	
	public void pay_dueAmount() {
		waitConditions.explicitWait_elementclickable(driver, actions, 5);
		click(actions);
		click(payDueAmount);
		waitConditions.explicitWait_elementvisibility(driver, payVia, 5);
		selectClass.dropdown(payVia, excelReader.getContactDetails(4, 2));
		javscriptExecuters.clickJsElement(chooseFile, driver);
		fileUpload.upload(readConfigProperty.paymentFile);
		selectClass.dropdown(paymentAccount, excelReader.getContactDetails(4, 3));
		sendKeys(paymentNote, excelReader.getContactDetails(4, 4));
		click(savePayment);
	}
			
	public String get_firstRowName() throws InterruptedException {
		Thread.sleep(3000);
		waitConditions.explicitWait_elementvisibility(driver, firstRowName, 5);
		String text = firstRowName.getText();
		return text;
	}
	
	

}
