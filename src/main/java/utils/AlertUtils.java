package utils;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AlertUtils {
	 public static void assertAlertText(WebDriver driver, String expectedText) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

	        String actualText = alert.getText();
	        if (!actualText.equals(expectedText)) {
	            throw new AssertionError("Alert text mismatch. Expected: '" + expectedText + "', but got: '" + actualText + "'");
	        }

	        alert.accept(); // or alert.dismiss() if needed
	    }
	 
	 public static String getAndAssertAlertMessage(WebDriver driver, String expectedText) throws TimeoutException {
		    Logger demoqaLog = LogManager.getLogger(AlertUtils.class); // Optional: centralize logger
		    

		    try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		        String actualText = alert.getText();
		        demoqaLog.info("📨 Alert detected with message: '{}'", actualText);

		        if (!actualText.equals(expectedText)) {
		        	demoqaLog.error("❌ Alert text mismatch. Expected: '{}', but got: '{}'", expectedText, actualText);
		            throw new AssertionError("Alert text mismatch. Expected: '" + expectedText + "', but got: '" + actualText + "'");
		        }

		        alert.accept();
		        demoqaLog.info("✅ Alert accepted successfully.");
		        return actualText;

		    } catch (NoAlertPresentException nae) {
		    	demoqaLog.error("🚫 No alert present when expected.", nae);
		        throw new AssertionError("No alert present when expected.");
		    } catch (Exception e) {
		    	demoqaLog.error("⚠️ Unexpected error during alert handling.", e);
		        throw e;
		    }
		}

}
