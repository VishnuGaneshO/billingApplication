package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonActions {
	WebDriver driver;

	public void click(WebElement element) {
		element.click();
	}

	public void sendKeys(WebElement element, String input) {

		element.sendKeys(input);
	}

	public String getText(WebElement message) {

		String msg = message.getText();
		return msg;
	}

	public void clear(WebElement element) {
		element.clear();

	}

}
