package utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSclick {
	public static void scrollAndClick(WebDriver driver, WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    try {
	        // Wait for element to be visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(element));
	        wait.until(ExpectedConditions.elementToBeClickable(element));

	        // Scroll into view and perform JS click
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	        js.executeScript("arguments[0].click();", element);

	        demoqaLog.info("✅ JS click executed on element: {}");
	    } catch (Exception e) {
	        demoqaLog.warn("⚠️ JS click failed, falling back to native click. Reason: {}");
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(element));
	            element.click();
	            demoqaLog.info("✅ Native click succeeded on element: {}");
	        } catch (Exception ex) {
	            demoqaLog.error("❌ Native click also failed. Reason: {}");
	            throw ex;
	        }
	    }
	}
}