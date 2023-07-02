package pageElements;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonActions;
import utilities.ExcelReader;
import utilities.SelectClass;
import utilities.WaitConditions;

public class UserPageElements extends CommonActions {
	WebDriver driver;
	WaitConditions waitConditions= new WaitConditions();
	ExcelReader excelReader= new ExcelReader();
	SelectClass selectClass = new SelectClass();
	
	
	public UserPageElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//span[text()='User Management']")
	public WebElement userManagement;
	
	@FindBy(xpath="//i[@class='fa fa-user']//following-sibling::span")
	public WebElement user;
	
	@FindBy(xpath="//a[@class='btn btn-block btn-primary']")
	public WebElement add;
	
	@FindBy(id="surname")
	public WebElement prefix;
	
	@FindBy(id="first_name")
	public WebElement firstName;
	
	@FindBy(id="last_name")
	public WebElement lastName;
	
	@FindBy(id="email")
	public WebElement email;
	
	@FindBy(id="select2-role-container")
	public WebElement role;
	
	@FindBy(xpath="//span[@class='select2-search select2-search--dropdown']/input")
	public WebElement roleInput;
	
	@FindBy(xpath="//ul[@id='select2-role-results']//child::li")
	public List<WebElement> roleList;
	
	@FindBy(id="username")
	public WebElement username;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(id="confirm_password")
	public WebElement confirmPassword;
	
	@FindBy(id="cmmsn_percent")
	public WebElement salesCommisionPercentage;
	
	@FindBy(xpath = "//input[@id='selected_contacts']//following-sibling::ins")
	public WebElement allowSelectedContact;
	
	@FindBy(xpath="//input[@class='input-icheck status']//following-sibling::ins")
	public WebElement isActive;
	
	@FindBy(id="submit_user_button")
	public WebElement save;
	
	@FindBy(xpath="//input[@type='search' and @class='form-control input-sm']")
	public WebElement search;
	
	@FindBy(xpath="//table[@id='users_table']/tbody/tr[1]/td[2]")
	public WebElement firstRow;
	
	@FindBy(xpath="//button[@class='btn btn-xs btn-danger delete_user_button']")
	public WebElement delete;
	
	@FindBy(xpath="//button[@class='swal-button swal-button--confirm swal-button--danger']")
	public WebElement ok;
	
	@FindBy(xpath="//table[@id='users_table']/tbody/tr[1]/td[5]/a[1]")
	public WebElement edit;
	
	@FindBy(id="submit_user_button")
	public WebElement update;
	
	@FindBy(xpath="//table[@id='users_table']/tbody[1]/tr[1]/td[1]")
	public WebElement noRecordsFound;
	
	public void add_User() {
		
		click(userManagement);
		waitConditions.explicitWait_elementvisibility(driver, user, 5);
		click(user);
		waitConditions.explicitWait_elementvisibility(driver, add, 5);
		click(add);
		sendKeys(prefix, excelReader.getUserDetails(1, 0));
		sendKeys(firstName, excelReader.getUserDetails(1, 1));
		sendKeys(lastName, excelReader.getUserDetails(1, 2));
		sendKeys(email, excelReader.getUserDetails(1, 3));
		click(role);
		waitConditions.explicitWait_elementvisibility(driver, userManagement, 3);
		sendKeys(roleInput, excelReader.getUserDetails(1, 4));
		selectClass.behavioralDropDown(roleList, excelReader.getUserDetails(1, 11));
		sendKeys(username, excelReader.getUserDetails(1, 5));
		sendKeys(password, excelReader.getUserDetails(1, 6));
		sendKeys(confirmPassword, excelReader.getUserDetails(1, 6));
		sendKeys(salesCommisionPercentage, excelReader.getUserDetails(1, 7));
		click(save);
		
	}
	
	public void user_Search(String key) {
		waitConditions.explicitWait_elementvisibility(driver, search, 5);
		clear(search);
		click(search);
		sendKeys(search, key);
		
	}
	
	public String get_Search_Result() {
		waitConditions.explicitWait_elementvisibility(driver, firstRow, 5);
		String text = firstRow.getText();
		return text;
	}
	
	public void edit_User(String key) {
		click(edit);
		waitConditions.explicitWait_elementvisibility(driver, lastName, 5);
		clear(lastName);
		sendKeys(lastName, key);
		click(update);
	}
	
	public void delete_User() {
		waitConditions.explicitWait_elementvisibility(driver, delete, 5);
		click(delete);
		waitConditions.explicitWait_elementclickable(driver, ok, 5);
		click(ok);
	}
	
	public String get_No_Records_Found() {
		waitConditions.explicitWait_elementvisibility(driver, noRecordsFound, 5);
		String text = noRecordsFound.getText();
		return text;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
