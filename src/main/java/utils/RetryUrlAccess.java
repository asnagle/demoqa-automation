package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class RetryUrlAccess {

    private static final Logger demoqaLog = LogManager.getLogger(RetryUrlAccess.class);

    /**
     * Navigate to a URL with retry logic.
     * After 2 failed attempts, PauseManager is triggered before further retries.
     * Retryable errors will be retried; fatal errors will abort execution.
     */
    public static WebDriver navigateWithRetry(WebDriver driver, String url, int maxRetries) {
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                logInfo("🌐 Navigating to URL (attempt " + (attempt + 1) + " of " + maxRetries + "): " + url);
                driver.get(url);

                // Check page source for server-side issues
                if (containsCriticalServerError(driver.getPageSource().toLowerCase())) {
                    logError("❌ Critical server error detected on attempt " + (attempt + 1) + " for " + url);
                    driver = restartDriver(driver);
                    attempt++;
                    continue;
                }

                logInfo("✅ Navigation successful on attempt " + (attempt + 1));
                return driver; // success → return driver

            } catch (WebDriverException e) {
                attempt++;
                logWarn("⚠️ Attempt " + attempt + " failed for " + url + ": " + e.getMessage());

                // Check if error is fatal → fail fast
                if (isFatalError(e)) {
                    logError("❌ Fatal error encountered (" + e.getMessage() + "). Cannot retry. Aborting tests.");
                    safeQuit(driver);
                    throw e; // stop execution
                }

                // Retryable error → restart driver and retry
                if (isRetryableError(e)) {
                    driver = restartDriver(driver);
                }

                // Trigger PauseManager after 2 failed attempts
                if (attempt == 2) {
                    logWarn("⏸ Triggering PauseManager after 2 failed attempts...");
                    PauseManager.handlePause(attempt, null); // ExtentTest not used here
                }

                // Max retries reached
                if (attempt >= maxRetries) {
                    logError("❌ All " + maxRetries + " navigation attempts failed for " + url);
                    safeQuit(driver);
                    return null;
                }

                // Backoff before next retry
                safeSleep(3000);
            }
        }
        return null;
    }

    // -------------------------------
    // 🔹 Error Detection Methods
    // -------------------------------

    private static boolean isRetryableError(WebDriverException e) {
        String msg = e.getMessage().toLowerCase();

        return msg.contains("500") ||
               msg.contains("502") ||
               msg.contains("503") ||
               msg.contains("504") ||
               msg.contains("507") ||
               msg.contains("508") ||
               msg.contains("509") ||
               msg.contains("520") ||
               msg.contains("521") ||
               msg.contains("522") ||
               msg.contains("523") ||
               msg.contains("524") ||
               msg.contains("525") ||
               msg.contains("526") ||
               msg.contains("527") ||

               // Transient network issues
               msg.contains("connection reset") ||
               msg.contains("connection closed") ||
               msg.contains("connection aborted") ||
               msg.contains("timed out") ||
               msg.contains("timeout") ||
               msg.contains("ssl handshake failed") ||
               msg.contains("ssl error") ||
               msg.contains("proxy error");
    }

    private static boolean isFatalError(WebDriverException e) {
        String msg = e.getMessage().toLowerCase();

        return msg.contains("dns lookup failed") ||
               msg.contains("dns not found") ||
               msg.contains("unknown host") ||
               msg.contains("name not resolved") ||
               msg.contains("internet disconnected") ||
               msg.contains("proxy connection failed");
    }

    private static boolean containsCriticalServerError(String pageSource) {
        return pageSource.contains("internal server error") ||
               pageSource.contains("bad gateway") ||
               pageSource.contains("service unavailable") ||
               pageSource.contains("gateway timeout") ||
               pageSource.contains("maintenance");
    }

    // -------------------------------
    // 🔹 Driver Management
    // -------------------------------

    private static WebDriver restartDriver(WebDriver driver) {
        logInfo("🔄 Restarting WebDriver...");
        safeQuit(driver);

        try {
            ChromeProfileCleaner.cleanChromeProfiles();
            logInfo("🧹 Chrome profile cleaned successfully");
        } catch (Exception e) {
            logWarn("⚠️ Failed to clean Chrome profile: " + e.getMessage());
        }

        WebDriver newDriver = DriverFactory.createDriver();
        logInfo("🚀 New WebDriver instance created");
        return newDriver;
    }

    private static void safeQuit(WebDriver driver) {
        try {
            if (driver != null) driver.quit();
        } catch (Exception ignored) {}
    }

    private static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    // -------------------------------
    // 🔹 Logging Helpers
    // -------------------------------

    private static void logInfo(String message) {
        demoqaLog.info(message);
    }

    private static void logWarn(String message) {
        demoqaLog.warn(message);
    }

    private static void logError(String message) {
        demoqaLog.error(message);
    }
}
