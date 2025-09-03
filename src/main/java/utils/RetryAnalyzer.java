package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger demoqaLog = LogManager.getLogger(RetryAnalyzer.class);
    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        Throwable cause = result.getThrowable();

        if (isRetryable(cause) && retryCount < maxRetryCount) {
            retryCount++;
            demoqaLog.warn("🔁 Retrying test '{}' | Attempt: {} | Reason: {}",
                    result.getName(),
                    retryCount + 1,
                    cause != null ? cause.getClass().getSimpleName() : "Unknown");

            return true;
        }

        demoqaLog.info("❌ No retry for test '{}' | Final failure reason: {}",
                result.getName(),
                cause != null ? cause.getClass().getSimpleName() : "Unknown");

        return false;
    }

    private boolean isRetryable(Throwable cause) {
        return cause instanceof TimeoutException ||
               cause instanceof ElementClickInterceptedException ||
               cause instanceof NoSuchElementException ||
               cause instanceof AssertionError;
    }
}