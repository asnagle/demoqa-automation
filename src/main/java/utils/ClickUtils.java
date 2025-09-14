package utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Centralized click utility:
 * - JS-first click
 * - Retries (3 attempts) with 3s pause
 * - Prefers re-finding by By locator when provided (avoids stale element)
 */
public class ClickUtils {

    private static final int DEFAULT_TIMEOUT = 10;   // seconds
    private static final int RETRY_WAIT_MS = 3000;   // 3 seconds between retries
    private static final int MAX_ATTEMPTS = 3;

    // ---------- JS click (WebElement) ----------
    public static void jSClick(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new RuntimeException("❌ JS Click failed on element: " + element, e);
        }
    }

    // ---------- JS click (By locator) ----------
    public static void jSClick(WebDriver driver, By locator) {
        WebElement element = waitUntilClickable(driver, locator);
        jSClick(driver, element);
    }

    // ---------- Primary API: smart click by locator (preferred) ----------
    public static void smartClick(WebDriver driver, By locator, boolean multiStrategy) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < MAX_ATTEMPTS) {
            try {
                // Try normal Selenium click on a fresh element
                WebElement target = waitUntilClickable(driver, locator);
                target.click();
                return;
            } catch (Exception e1) {
                lastException = e1;

                // Try JS-first fallback on fresh element
                if (multiStrategy) {
                    try {
                        WebElement fresh = driver.findElement(locator);
                        jSClick(driver, fresh);
                        return;
                    } catch (Exception e2) {
                        lastException = e2;
                    }

                    // try by id of the fresh element
                    try {
                        WebElement fresh = driver.findElement(locator);
                        String id = fresh.getAttribute("id");
                        if (id != null && !id.isEmpty()) {
                            WebElement byId = waitUntilClickable(driver, By.id(id));
                            jSClick(driver, byId);
                            return;
                        }
                    } catch (Exception e3) {
                        lastException = e3;
                    }

                    // try by first css class
                    try {
                        WebElement fresh = driver.findElement(locator);
                        String cssClass = fresh.getAttribute("class");
                        if (cssClass != null && !cssClass.isEmpty()) {
                            String firstClass = cssClass.split("\\s+")[0];
                            WebElement byCss = waitUntilClickable(driver, By.cssSelector("." + firstClass));
                            jSClick(driver, byCss);
                            return;
                        }
                    } catch (Exception e4) {
                        lastException = e4;
                    }
                }
            }

            // Wait before retry
            attempts++;
            try {
                Thread.sleep(RETRY_WAIT_MS);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException("❌ smartClick failed after " + MAX_ATTEMPTS + " attempts", lastException);
    }

    // ---------- Backwards-compatible: element + locator preference ----------
    public static void smartClick(WebDriver driver, WebElement element, By originalLocator, boolean multiStrategy) {
        // If locator provided, prefer working through locator (fresh element on each attempt)
        if (originalLocator != null) {
            smartClick(driver, originalLocator, multiStrategy);
        } else {
            smartClick(driver, element, multiStrategy);
        }
    }

    // ---------- Backwards-compatible: element only (less reliable) ----------
    public static void smartClick(WebDriver driver, WebElement element, boolean multiStrategy) {
        int attempts = 0;
        Exception lastException = null;

        while (attempts < MAX_ATTEMPTS) {
            try {
                WebElement clickable = waitUntilClickable(driver, element);
                clickable.click();
                return;
            } catch (Exception e1) {
                lastException = e1;

                // JS fallback
                if (multiStrategy) {
                    try {
                        jSClick(driver, element);
                        return;
                    } catch (Exception e2) {
                        lastException = e2;
                    }

                    // by id
                    try {
                        String id = element.getAttribute("id");
                        if (id != null && !id.isEmpty()) {
                            WebElement byId = waitUntilClickable(driver, By.id(id));
                            jSClick(driver, byId);
                            return;
                        }
                    } catch (Exception e3) {
                        lastException = e3;
                    }

                    // by class
                    try {
                        String cssClass = element.getAttribute("class");
                        if (cssClass != null && !cssClass.isEmpty()) {
                            WebElement byCss = waitUntilClickable(driver, By.cssSelector("." + cssClass.split("\\s+")[0]));
                            jSClick(driver, byCss);
                            return;
                        }
                    } catch (Exception e4) {
                        lastException = e4;
                    }
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

    // ---------------- Wait helpers ----------------
    private static WebElement waitUntilClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private static WebElement waitUntilClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
