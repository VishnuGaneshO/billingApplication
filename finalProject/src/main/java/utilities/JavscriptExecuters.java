package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavscriptExecuters {
	
	public void clickJsElement(WebElement element, WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

}
