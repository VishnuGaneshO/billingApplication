package pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonActions;

public class LoginPageElements extends CommonActions {
	WebDriver driver;
	
	
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
	
	public void clickLogin(String id,String key) {
		sendKeys(username, id);
		sendKeys(password, key);
		click(login);
	}

}
