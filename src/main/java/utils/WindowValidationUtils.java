package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.HasDevTools;
//import org.openqa.selenium.devtools.v137.page.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.devtools.v137.page.model.FrameId;
//import java.io.File;
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
    public static void switchToNewWindowAndValidate(
            WebDriver driver,
            String originalHandle,
            Logger demoqaLog
    ) {
        // Wait for new window
        String newWindowHandle = waitForNewWindow(driver, originalHandle);
        driver.switchTo().window(newWindowHandle);
        demoqaLog.info("🔀 Switched to new window: {}", newWindowHandle);

        // Validate that a different window opened
        if (originalHandle.equals(newWindowHandle)) {
            throw new AssertionError(
                    String.format("❌ Expected a new window, but got the same handle [%s]", newWindowHandle)
            );
        }

        demoqaLog.info("✅ Verified new popup window opened successfully.");

        // Switch back (do not close, teardown will handle that)
        driver.switchTo().window(originalHandle);
        demoqaLog.info("↩️ Switched back to original window: {}", originalHandle);
    }

    
}
