package pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonActions;
import utilities.WaitConditions;

public class LoginPageElements extends CommonActions {
	WebDriver driver;
	WaitConditions waitConditions= new WaitConditions();
	
	
	public LoginPageElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(id = "username")
	public WebElement username;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	public WebElement login;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-sm']")
	public WebElement endTour;
	
	@FindBy(xpath = "//section[@class='content-header']/h1")
	public WebElement welcome;
	
	@FindBy(xpath = "//span[@class='help-block']/strong")
	public WebElement invalidCredentials;
	
	public void login_With_Valid_Credentials(String id,String key) {
		sendKeys(username, id);
		sendKeys(password, key);
		click(login);
		waitConditions.explicitWait_elementclickable(driver, endTour, 10);
		click(endTour);
	}
	
	public void login_With_Invalid_Credentials(String id,String key) {
		
		sendKeys(username, id);
		sendKeys(password, key);
		click(login);
		clear(username);
	}
	
	public String get_Welcome_Text() {
		String text = welcome.getText();
		return text;
	}
	
	public String get_Invalid_Credentials_Text() {
		String text = invalidCredentials.getText();
		return text;
		
	}
	

}
