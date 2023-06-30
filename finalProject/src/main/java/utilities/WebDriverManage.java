package utilities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManage {
	
	WebDriver driver;

	
	public WebDriver launchBrowser(String browser, String url) {

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions objchromeoptions = new ChromeOptions();
			objchromeoptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(objchromeoptions);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

		}
		if (browser.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

		}
		if (browser.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

		}

		return driver;

	}

}
