package utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Centralized click handler with logging:
 * - Logs via demoqaLog + console
 * - JS-first click
 * - Retries (3 attempts) with 3s pause
 * - Prefers re-finding by By locator when provided (avoids stale element)
 */
public class ClickHandler {

    private static final int DEFAULT_TIMEOUT = 10;   // seconds
    private static final int RETRY_WAIT_MS = 3000;   // 3 seconds between retries
    private static final int MAX_ATTEMPTS = 3;

    // Unified log helper (both console + demoqaLog)
    private static void log(String msg) {
        System.out.println(msg);      // console
        demoqaLog.info(msg);          // your logger
    }

    // ---------- JS click ----------
    private static void jSClick(WebDriver driver, WebElement element) {
        log("🔹 Attempting JS click...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        js.executeScript("arguments[0].click();", element);
    }

    // ---------- Smart click by locator ----------
    public static void smartClick(WebDriver driver, By locator) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < MAX_ATTEMPTS) {
            try {
                WebElement element = waitUntilClickable(driver, locator);
                log("🔹 Trying JS click by locator (attempt " + (attempts + 1) + ")");
                jSClick(driver, element);
                return;
            } catch (Exception jsEx) {
                lastException = jsEx;

                try {
                    log("⚡ Falling back to native click by locator (attempt " + (attempts + 1) + ")");
                    WebElement element = waitUntilClickable(driver, locator);
                    element.click();
                    return;
                } catch (Exception nativeEx) {
                    lastException = nativeEx;
                }

                try {
                    log("🔄 Retrying via element ID (attempt " + (attempts + 1) + ")");
                    WebElement element = driver.findElement(locator);
                    String id = element.getAttribute("id");
                    if (id != null && !id.isEmpty()) {
                        WebElement byId = waitUntilClickable(driver, By.id(id));
                        jSClick(driver, byId);
                        return;
                    }
                } catch (Exception idEx) {
                    lastException = idEx;
                }

                try {
                    log("🔄 Retrying via first CSS class (attempt " + (attempts + 1) + ")");
                    WebElement element = driver.findElement(locator);
                    String cssClass = element.getAttribute("class");
                    if (cssClass != null && !cssClass.isEmpty()) {
                        String firstClass = cssClass.split("\\s+")[0];
                        WebElement byCss = waitUntilClickable(driver, By.cssSelector("." + firstClass));
                        jSClick(driver, byCss);
                        return;
                    }
                } catch (Exception cssEx) {
                    lastException = cssEx;
                }
            }

            attempts++;
            try {
                log("⏳ Waiting " + (RETRY_WAIT_MS / 1000) + "s before retry...");
                Thread.sleep(RETRY_WAIT_MS);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException("❌ smartClick failed after " + MAX_ATTEMPTS + " attempts", lastException);
    }

    // ---------- Smart click by WebElement ----------
    public static void smartClick(WebDriver driver, WebElement element) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < MAX_ATTEMPTS) {
            try {
                log("🔹 Trying JS click on WebElement (attempt " + (attempts + 1) + ")");
                jSClick(driver, element);
                return;
            } catch (Exception jsEx) {
                lastException = jsEx;

                try {
                    log("⚡ Falling back to native click on WebElement (attempt " + (attempts + 1) + ")");
                    WebElement clickable = waitUntilClickable(driver, element);
                    clickable.click();
                    return;
                } catch (Exception nativeEx) {
                    lastException = nativeEx;
                }

                try {
                    log("🔄 Retrying via element ID (attempt " + (attempts + 1) + ")");
                    String id = element.getAttribute("id");
                    if (id != null && !id.isEmpty()) {
                        WebElement byId = waitUntilClickable(driver, By.id(id));
                        jSClick(driver, byId);
                        return;
                    }
                } catch (Exception idEx) {
                    lastException = idEx;
                }

                try {
                    log("🔄 Retrying via first CSS class (attempt " + (attempts + 1) + ")");
                    String cssClass = element.getAttribute("class");
                    if (cssClass != null && !cssClass.isEmpty()) {
                        String firstClass = cssClass.split("\\s+")[0];
                        WebElement byCss = waitUntilClickable(driver, By.cssSelector("." + firstClass));
                        jSClick(driver, byCss);
                        return;
                    }
                } catch (Exception cssEx) {
                    lastException = cssEx;
                }
            }

            attempts++;
            try {
                log("⏳ Waiting " + (RETRY_WAIT_MS / 1000) + "s before retry...");
                Thread.sleep(RETRY_WAIT_MS);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException("❌ smartClick failed after " + MAX_ATTEMPTS + " attempts", lastException);
    }

    // ---------- Wait helpers ----------
    private static WebElement waitUntilClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private static WebElement waitUntilClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
