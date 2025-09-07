package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitForElement {
	public static void waitAndClick(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public static WebElement waitUntilVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static boolean isElementVisible(WebDriver driver, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	public static boolean isElementVisible(WebDriver driver, WebElement element) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOf(element));
	        return true;
	    } catch (TimeoutException e) {
	        return false;
	    }
	}

	public static void waitAndClick(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static boolean waitUntilInvisible(WebDriver driver, By locator, int timeoutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			return false;
		}
	}

	public static boolean waitUntilInvisible(WebDriver driver, By locator) {
		return waitUntilInvisible(driver, locator, 5);
	}
	
	public static WebElement getVisibleElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
	public static WebElement waitUntilInteractable(WebDriver driver, WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static boolean waitForFrameText(WebDriver driver, WebElement frameElement, WebElement contentElement, String expectedText, int timeoutSeconds) {
	    try {
	        driver.switchTo().frame(frameElement);
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        boolean textPresent = wait.until(ExpectedConditions.textToBePresentInElement(contentElement, expectedText));
	        driver.switchTo().defaultContent(); // Optional: switch back after check
	        return textPresent;
	    } catch (TimeoutException e) {
	        driver.switchTo().defaultContent(); // Ensure we exit the frame even on failure
	        return false;
	    }
	}
	
	public static boolean waitForFrameAndSwitch(WebDriver driver, By frameLocator, int timeoutSeconds) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	        return true;
	    } catch (TimeoutException e) {
	        return false;
	    }
	}

}
