package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ClickUtils;
import utils.demoqaLog;

/**
 * BasePage for POM classes.
 * Pages should extend this class and call clickElement(locator, "Friendly Name")
 */
public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Generic click wrapper using ClickUtils.smartClick.
     * Uses locator-first strategy (smartClick will re-find element and use JS-first internally).
     */
    protected void clickElement(By locator, String description) {
        try {
            demoqaLog.info("Clicking on " + description + "...");
            // Use locator-based API so ClickUtils can re-find fresh elements on retries
            ClickUtils.smartClick(driver, locator, true);
            demoqaLog.info("Clicked on " + description + ".");
        } catch (Exception e) {
            demoqaLog.error("Failed to click on " + description + " - " + e.getMessage());
            throw e;
        }
    }

    /**
     * If you ever need to click using an existing WebElement and still provide locator as fallback:
     * ClickUtils.smartClick(driver, webElement, locator, true)
     */
}
