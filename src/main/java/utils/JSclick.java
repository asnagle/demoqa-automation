package utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;


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
	
	public static boolean safeClick(WebDriver driver, WebElement element, Logger demoqaLog, String elementName) {
        boolean clicked = false;

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
            clicked = true;
            demoqaLog.info("✅ Native click succeeded on '{}'", elementName);
        } catch (Exception nativeEx) {
            demoqaLog.warn("⚠️ Native click failed on '{}': {}. Trying JS fallback.", elementName, nativeEx.getMessage());
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                clicked = true;
                demoqaLog.info("✅ JS click succeeded on '{}'", elementName);
            } catch (Exception jsEx) {
                demoqaLog.error("❌ JS click also failed on '{}'. Reason: {}", elementName, jsEx.getMessage());
            }
        }

        return clicked;
    }

}