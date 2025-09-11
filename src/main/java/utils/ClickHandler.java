package utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Centralized click handler:
 * - JS-first click
 * - Retries (3 attempts) with 3s pause
 * - Prefers re-finding by By locator when provided (avoids stale element)
 */
public class ClickHandler {

    private static final int DEFAULT_TIMEOUT = 10;   // seconds
    private static final int RETRY_WAIT_MS = 3000;   // 3 seconds between retries
    private static final int MAX_ATTEMPTS = 3;

    // ---------- JS click ----------
    private static void jSClick(WebDriver driver, WebElement element) {
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
                jSClick(driver, element);
                return;
            } catch (Exception jsEx) {
                lastException = jsEx;

                try {
                    WebElement element = waitUntilClickable(driver, locator);
                    element.click();
                    return;
                } catch (Exception nativeEx) {
                    lastException = nativeEx;
                }

                try {
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
                jSClick(driver, element);
                return;
            } catch (Exception jsEx) {
                lastException = jsEx;

                try {
                    WebElement clickable = waitUntilClickable(driver, element);
                    clickable.click();
                    return;
                } catch (Exception nativeEx) {
                    lastException = nativeEx;
                }

                try {
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