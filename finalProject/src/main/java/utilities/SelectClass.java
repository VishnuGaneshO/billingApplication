package utilities;

import java.util.List;

import org.openqa.selenium.WebElement;

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

}
