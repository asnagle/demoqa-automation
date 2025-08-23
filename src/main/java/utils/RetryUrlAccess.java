package utils;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryUrlAccess {

    private static final Logger demoqaLog = LogManager.getLogger(RetryUrlAccess.class);
    private static final int DEFAULT_WAIT_MS = 3000;

    public static void navigateWithRetry(WebDriver driver, String url, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                driver.get(url);
                demoqaLog.info("Successfully navigated to: {}", url);
                return;
            } catch (WebDriverException e) {
                if (isRetryable(e)) {
                    demoqaLog.warn("Attempt {} failed due to retryable error: {}. Retrying after {}ms...", 
                                   attempt, sanitize(e.getMessage()), DEFAULT_WAIT_MS);
                    waitBeforeRetry();
                } else {
                    demoqaLog.error("Navigation failed due to non-retryable error: {}", sanitize(e.getMessage()), e);
                    throw e;
                }
            }
        }

        throw new RuntimeException("Failed to navigate to " + url + " after " + maxAttempts + " attempts");
    }

    private static boolean isRetryable(WebDriverException e) {
        String msg = sanitize(e.getMessage());
        return msg.contains("502") || msg.contains("bad gateway") ||
               msg.contains("500") || msg.contains("this page returned a 500 status code") ||
               e instanceof TimeoutException;
    }

    private static void waitBeforeRetry() {
        try {
            Thread.sleep(DEFAULT_WAIT_MS);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            demoqaLog.error("Retry interrupted during wait period.", ie);
            throw new RuntimeException("Retry interrupted", ie);
        }
    }

    private static String sanitize(String message) {
        return message == null ? "" : message.toLowerCase();
    }
}