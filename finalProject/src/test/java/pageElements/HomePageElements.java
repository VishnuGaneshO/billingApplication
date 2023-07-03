package pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonActions;
import utilities.ExcelReader;
import utilities.JavscriptExecuters;
import utilities.WaitConditions;

public class HomePageElements extends CommonActions {
	WebDriver driver;
	WaitConditions waitConditions= new WaitConditions();
	ExcelReader excelReader= new ExcelReader();
	JavscriptExecuters javscriptExecuters = new JavscriptExecuters();
	
	public HomePageElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath="//a[@class='dropdown-toggle']")
	public WebElement user;
	
	@FindBy(xpath="//div[@class='pull-right']")
	public WebElement signOut;
	
	@FindBy(xpath="//li[@class='user-header']/p")
	public WebElement profileName;
	
	@FindBy(xpath="//div[@class='panel-heading']")
	public WebElement loginPage;
	
	public void signOut() {
		waitConditions.explicitWait_elementvisibility(driver, user, 5);
		click(user);
		click(signOut);
	}
	
	public String get_ProfileName() {
		String text= profileName.getText();
		return text;
	}
	
	public String get_LoginPage() {
		String text = loginPage.getText();
		return text;
	}
	
	
	
	
}
