package utils;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageLoadHandler {

    public static void waitUntilLoaded(WebDriver driver, int timeoutSeconds) {
        demoqaLog.info("⏳ Waiting for page to fully load (timeout: " + timeoutSeconds + "s)");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        try {
            wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
            );
            demoqaLog.info("✅ Page load completed.");
        } catch (Exception e) {
            demoqaLog.warn("⚠️ Page did not load within " + timeoutSeconds + " seconds.");
        }
    }
}