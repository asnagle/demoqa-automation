package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class closeAds {
	private WebDriver driver;
	public void dismissAdAndClick(By targetLocator, String adIframeId) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(adIframeId)));
	    } catch (TimeoutException e) {
	        System.out.println("Ad still visible. Attempting to remove: " + adIframeId);
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript(
	            "var ad = document.getElementById('" + adIframeId + "'); if (ad) { ad.remove(); }"
	        );
	    }

	    WebElement target = driver.findElement(targetLocator);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", target);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
	}

}
