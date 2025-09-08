package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class RetryUrlAccess {

    private static final Logger demoqaLog = LogManager.getLogger(RetryUrlAccess.class);

    public static WebDriver navigateWithRetry(WebDriver driver, String url, int maxRetries) {
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                demoqaLog.info("Navigating to URL (attempt {}): {}", attempt + 1, url);
                driver.get(url);
                return driver; // success
            } catch (WebDriverException e) {
                attempt++;
                demoqaLog.warn("Navigation attempt {} failed: {}", attempt, e.getMessage());

                // Wait before retrying
                try { Thread.sleep(3000); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }

                // Final attempt failed → quit driver
                if (attempt >= maxRetries) {
                    demoqaLog.error("❌ All {} navigation attempts failed for {}", maxRetries, url);
                    try { driver.quit(); } catch (Exception ignored) {}
                    return null; // signal failure
                }
            }
        }
        return null;
    }
}
