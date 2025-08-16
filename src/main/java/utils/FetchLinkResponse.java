package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class FetchLinkResponse {
	private static final Logger demoqaLog = LogManager.getLogger(FetchLinkResponse.class);

	public static String fetchLinkResponseText(WebDriver driver, WebElement element) {
        demoqaLog.info("Fetching link response...");

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            String responseText = element.getText().trim();
            demoqaLog.info("Response fetched: {}", responseText);
            System.out.println(responseText);
            return responseText;
        } catch (Exception e) {
            demoqaLog.error("Error while fetching response", e);
            return null;
        }

    }

}
