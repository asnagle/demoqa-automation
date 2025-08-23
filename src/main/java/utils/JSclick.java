package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSclick {
	public static void scrollAndClick(WebDriver driver, WebElement element) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	        js.executeScript("arguments[0].click();", element);
//	        demoqaLog.info("✅ JS click executed on element: {}", element);
	    } catch (Exception e) {
	        demoqaLog.warn("⚠️ JS click failed, falling back to native click.");
	        element.click();
	    }
	}
}