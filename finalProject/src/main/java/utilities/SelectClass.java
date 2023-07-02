package utilities;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SelectClass extends CommonActions {
	WaitConditions waitConditions = new WaitConditions();

	public void behavioralDropDown(List<WebElement> elements, String text) {

		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase(text)) {
				click(element);
				break;
			}
		}

	}
	
	public void dropdown(WebElement element, String byVisibleText)  {
		Select select = new Select(element);
		select.selectByVisibleText(byVisibleText);

	}
	
	

}
