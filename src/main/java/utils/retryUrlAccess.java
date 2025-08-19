package utils;


import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class retryUrlAccess {

    public static void navigateWithRetry(WebDriver driver, String url, int maxAttempts) {
        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                driver.get(url);
                demoqaLog.info("Successfully navigated to: " + url);
                return;
            } catch (WebDriverException e) {
                String msg = e.getMessage().toLowerCase();

                if (msg.contains("502") || msg.contains("500") || msg.contains("This page returned a 500 status code")|| e instanceof TimeoutException) {
                    attempts++;
                    demoqaLog.warn("Navigation failed due to 502 or timeout. Retrying... Attempt " + attempts);

                    try {
                        Thread.sleep(3000); // Optional backoff
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        demoqaLog.error("Retry interrupted during wait period.");
                        throw new RuntimeException("Retry interrupted", ie);
                    }

                } else {
                    demoqaLog.error("Navigation failed due to non-retryable error: " + e.getMessage());
                    throw e;
                }
            }
        }

        throw new RuntimeException("Failed to navigate to " + url + " after " + maxAttempts + " attempts");
    }
}