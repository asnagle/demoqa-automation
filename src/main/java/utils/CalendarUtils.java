package utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CalendarUtils {

    public static void assertCalendarOpened(WebDriver driver, Logger demoqaLog) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            demoqaLog.info("🔍 Waiting for calendar popup to appear...");
            WebElement calendarPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("react-datepicker")));

            Assert.assertTrue(calendarPopup.isDisplayed(), "❌ Calendar popup not visible after input click");
            demoqaLog.info("✅ Calendar popup is visible after clicking the input");
        } catch (Exception e) {
            demoqaLog.error("❌ Failed to detect calendar popup after input click", e);
            throw new AssertionError("Calendar popup did not appear", e);
        }
    }
}