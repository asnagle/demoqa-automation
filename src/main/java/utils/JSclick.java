package utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

public class JSclick {

    // ✅ Existing WebElement version (unchanged, just cleaned logs)
    public static void scrollAndClick(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            js.executeScript("arguments[0].click();", element);

            System.out.println("✅ JS click executed on element.");
        } catch (Exception e) {
            System.out.println("⚠️ JS click failed, trying native click. Reason: " + e.getMessage());
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                System.out.println("✅ Native click succeeded.");
            } catch (Exception ex) {
                System.out.println("❌ Native click also failed. Reason: " + ex.getMessage());
                throw ex;
            }
        }
    }

    // ✅ Existing WebElement version with Logger
    public static void scrollAndClick(WebDriver driver, WebElement element, Logger demoqaLog, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            js.executeScript("arguments[0].click();", element);

            demoqaLog.info("✅ JS click executed on '{}'", elementName);
        } catch (Exception e) {
            demoqaLog.warn("⚠️ JS click failed on '{}'. Falling back to native click. Reason: {}", elementName, e.getMessage());
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                demoqaLog.info("✅ Native click succeeded on '{}'", elementName);
            } catch (Exception ex) {
                demoqaLog.error("❌ Native click also failed on '{}'. Reason: {}", elementName, ex.getMessage());
                throw ex;
            }
        }
    }

    // ✅ New By locator version (with retry logic)
    public static void scrollAndClick(WebDriver driver, By locator, Logger demoqaLog, String elementName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int attempts = 0;

        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                wait.until(ExpectedConditions.elementToBeClickable(locator));

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                js.executeScript("arguments[0].click();", element);

                demoqaLog.info("✅ JS click executed on '{}'", elementName);
                return; // success → exit method
            } catch (Exception e) {
                demoqaLog.warn("⚠️ Attempt {} JS click failed on '{}'. Reason: {}", attempts + 1, elementName, e.getMessage());
                try {
                    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                    element.click();
                    demoqaLog.info("✅ Native click succeeded on '{}'", elementName);
                    return;
                } catch (Exception ex) {
                    demoqaLog.error("❌ Attempt {} Native click also failed on '{}'. Reason: {}", attempts + 1, elementName, ex.getMessage());
                    attempts++;
                    if (attempts >= 3) {
                        throw ex; // fail after 3 tries
                    }
                }
            }
        }
    }

    // ✅ Existing safeClick stays (handy utility)
    public static boolean safeClick(WebDriver driver, WebElement element, Logger demoqaLog, String elementName) {
        boolean clicked = false;

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
            clicked = true;
            demoqaLog.info("✅ Native click succeeded on '{}'", elementName);
        } catch (Exception nativeEx) {
            demoqaLog.warn("⚠️ Native click failed on '{}': {}. Trying JS fallback.", elementName, nativeEx.getMessage());
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                clicked = true;
                demoqaLog.info("✅ JS click succeeded on '{}'", elementName);
            } catch (Exception jsEx) {
                demoqaLog.error("❌ JS click also failed on '{}'. Reason: {}", elementName, jsEx.getMessage());
            }
        }

        return clicked;
    }
}
