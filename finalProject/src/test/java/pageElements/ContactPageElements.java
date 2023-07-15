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
	
	@FindBy(xpath="//label[text()='Pay term:']/following-sibling::select")
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
	
	public void add_suppliers(String contactName, String businessName, String payTermNumber, String mobileNumber, String payTermMonth) {
		waitConditions.explicitWait_elementclickable(driver, contacts, 5);
		click(contacts);
		waitConditions.explicitWait_elementclickable(driver, suppliers, 5);
		click(suppliers);
		waitConditions.explicitWait_elementvisibility(driver, add, 5);
		click(add);
		waitConditions.explicitWait_elementvisibility(driver, name, 5);
		sendKeys(name, contactName);
		sendKeys(businessType, businessName);
		sendKeys(payTerm,payTermNumber);
		selectClass.dropdown(payTermSelect, payTermMonth);
		sendKeys(mobile, mobileNumber);
		click(save);
		
	}
	
	public void search(String key) {
		waitConditions.explicitWait_elementvisibility(driver, search, 5);
		clear(search);
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
	
	
			
	public String get_firstRowName() throws InterruptedException {
		Thread.sleep(3000);
		waitConditions.explicitWait_elementvisibility(driver, firstRowName, 5);
		String text = firstRowName.getText();
		return text;
	}
	
	

}
