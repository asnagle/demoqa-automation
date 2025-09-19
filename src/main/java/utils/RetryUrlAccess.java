package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class RetryUrlAccess {

    private static final Logger demoqaLog = LogManager.getLogger(RetryUrlAccess.class);

    /**
     * Navigate to URL with retries.
     * Ensures demoqaBase.driver stays valid if driver restarts.
     */
    public static WebDriver navigateWithRetry(WebDriver driver, String url, int maxRetries) {
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                demoqaLog.info("🌐 Navigating to URL (attempt {}): {}", attempt + 1, url);
                driver.get(url);

                // Light check for obvious server error
                String pageSource = driver.getPageSource().toLowerCase();
                if (containsCriticalServerError(pageSource)) {
                    demoqaLog.error("❌ Server error detected on attempt {} for {}", attempt + 1, url);
                    driver = restartDriver(driver);
                    attempt++;
                    continue;
                }

                demoqaLog.info("✅ Navigation successful on attempt {}", attempt + 1);
                return driver; // Success
            } catch (WebDriverException e) {
                attempt++;
                demoqaLog.warn("⚠️ Navigation attempt {} failed: {}", attempt, e.getMessage());

                safeSleep(3000);

                if (attempt >= maxRetries) {
                    demoqaLog.error("❌ All {} navigation attempts failed for {}", maxRetries, url);
                    safeQuit(driver);
                    return null;
                }

                driver = restartDriver(driver);
            }
        }
        return null;
    }

    private static boolean containsCriticalServerError(String pageSource) {
        // Stricter check: look for phrases, not just numbers
        return pageSource.contains("internal server error")
            || pageSource.contains("bad gateway")
            || pageSource.contains("service unavailable");
    }

    private static WebDriver restartDriver(WebDriver driver) {
        demoqaLog.info("🔄 Restarting browser (cleaning Chrome profile first)...");
        safeQuit(driver);

        try {
            ChromeProfileCleaner.cleanChromeProfiles();
            demoqaLog.info("🧹 Chrome profile cleaned successfully");
        } catch (Exception e) {
            demoqaLog.error("⚠️ Failed to clean Chrome profile: {}", e.getMessage());
        }

        WebDriver newDriver = DriverFactory.createDriver();
        demoqaLog.info("🚀 New WebDriver instance created");
        return newDriver;
    }

    private static void safeQuit(WebDriver driver) {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception ignored) {}
    }

    private static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
