package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.time.Duration;
import java.util.Set;

public class WindowValidationUtils {

    // 🔹 Helper method used by both cases
    private static String waitForNewWindow(WebDriver driver, String originalHandle) {
        Set<String> allHandles = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> {
                    Set<String> handles = d.getWindowHandles();
                    return handles.size() > 1 ? handles : null;
                });

        allHandles.remove(originalHandle);
        if (allHandles.isEmpty()) {
            throw new RuntimeException("No new window detected after click");
        }

        return allHandles.iterator().next();
    }

    // 🔹 Case 1: New tab/window WITH elements
    public static String switchToNewTabAndValidateText(
            WebDriver driver,
            String originalHandle,
            By locator,
            String expectedText,
            Logger demoqaLog
    ) {
        String newTabHandle = waitForNewWindow(driver, originalHandle);
        driver.switchTo().window(newTabHandle);
        demoqaLog.info("Switched to new tab: {}", newTabHandle);

        WebElement resultElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        String actualText = resultElement.getText().trim();
        demoqaLog.info("New tab content: '{}'", actualText);

        Assert.assertEquals(actualText, expectedText.trim(), "Text mismatch in new tab");

        driver.switchTo().window(originalHandle);
        return actualText;
    }

    // 🔹 Case 2: Message-only window (no DOM elements)
    public static String switchToNewWindowAndValidateText(
            WebDriver driver,
            String originalHandle,
            String expectedText,
            Logger demoqaLog
    ) {
        String newWindowHandle = waitForNewWindow(driver, originalHandle);
        driver.switchTo().window(newWindowHandle);
        demoqaLog.info("🔀 Switched to new window: {}", newWindowHandle);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
     // Pass to OCR engine like Tesseract or Google Vision
        demoqaLog.info("📸 Screenshot captured for OCR analysis: {}", screenshot.getAbsolutePath());


        // Wait for non-empty body
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                d -> {
                    String bodyText = (String) ((JavascriptExecutor) d)
                        .executeScript("return document.body?.textContent?.trim();");
                    return bodyText != null && !bodyText.isBlank();
                }
            );
        } catch (TimeoutException te) {
            demoqaLog.error("⏳ Body text never appeared in new window: {}", newWindowHandle);
            driver.close();
            driver.switchTo().window(originalHandle);
            throw new RuntimeException("Renderer stalled or body not populated", te);
        }

        // Extract text
        String actualText = (String) ((JavascriptExecutor) driver)
            .executeScript("return document.body.textContent.trim();");
        demoqaLog.info("📄 Extracted content: '{}'", actualText);

        Assert.assertEquals(
            actualText.trim(),
            expectedText.trim(),
            String.format("❌ Text mismatch in window [%s]", newWindowHandle)
        );

        driver.switchTo().window(originalHandle);
        demoqaLog.info("↩️ Switched back to original window: {}", originalHandle);

        return actualText;
    }
    
    private static String extractBodyTextViaJS(WebDriver driver, Logger log) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (String) js.executeScript("return document.body?.textContent?.trim();");
        } catch (Exception e) {
            log.error("❌ JS extraction failed: {}", e.getMessage());
            return null;
        }
    }
    
    private static void dumpWindowMetadata(WebDriver driver, Logger log) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String url = (String) js.executeScript("return window.location.href;");
            String title = (String) js.executeScript("return document.title;");
            log.debug("🧭 Window URL: {}", url);
            log.debug("📝 Window Title: {}", title);
        } catch (Exception e) {
            log.warn("⚠️ Failed to dump window metadata: {}", e.getMessage());
        }
    }
}
