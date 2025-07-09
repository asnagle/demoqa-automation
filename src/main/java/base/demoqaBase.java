package base;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

//import utils.Log;

//import utils.Log;

public class demoqaBase {
	
	protected WebDriver driver;
	
	@BeforeMethod
	public void setUP () {
		
//		Log.info("Starting Web Browser...");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");  // ← This is the key!
		options.addArguments("--start-maximized");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
				+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
		driver = new ChromeDriver(options);
		driver.get("https://demoqa.com/");
	}
	
	@AfterMethod
	public void TearDown() {
		
		if (driver != null) {
//			Log.info("Closing the Browser...");
			driver.quit();
		}
	}

}
