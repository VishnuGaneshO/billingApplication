package testCases;

import org.testng.annotations.Test;
import pageElements.LoginPageElements;
import utilities.ReadConfigProperty;
import utilities.WebDriverManage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class LoginPage extends WebDriverManage{
	public static WebDriver driver;
	LoginPageElements objLogin;
	ReadConfigProperty objProp = new ReadConfigProperty();
	
  @Test(priority =0, enabled = true,dataProvider = "logins")
  public void login(String id, String password) {
	  objLogin.clickLogin(id, password);
	  
  }
  @BeforeTest(alwaysRun = true)
  public void beforeTest() {
	  driver = launchBrowser(objProp.browser, objProp.url);
	  objLogin = new LoginPageElements(driver);
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
