package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import enums.TestContext;

public class DatePickerUtils {

    /**
     * Selects a date in a React-based date picker widget.
     * @param driver WebDriver instance
     * @param inputLocator Locator for the date input field
     * @param assertLocator Locator for the field to assert selected value
     * @param date LocalDate to select
     * @param context TestContext to determine expected format
     */
    public static void selectDate(WebDriver driver, By inputLocator, By assertLocator, LocalDate date, TestContext context) {
        try {
            String day = String.valueOf(date.getDayOfMonth());
            String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = String.valueOf(date.getYear());

            // Step 1: Open the calendar
            WebElement inputField = driver.findElement(inputLocator);
            JSclick.scrollAndClick(driver, inputField);

            // Step 2: Wait for dropdowns to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("react-datepicker__month-select")));
            new Select(monthDropdown).selectByVisibleText(month);

            WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
            new Select(yearDropdown).selectByVisibleText(year);

            // Step 3: Select the day
            String paddedDay = String.format("%02d", Integer.parseInt(day));
            String daySelector = String.format("div.react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", paddedDay);
            WebElement dayElement = driver.findElement(By.cssSelector(daySelector));

            if (dayElement.isDisplayed() && dayElement.isEnabled()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dayElement);
                System.out.println("✅ Date selected: " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            } else {
                System.out.println("⚠️ Day element not interactable: " + paddedDay);
            }

        } catch (Exception e) {
            System.out.println("❌ Failed to select date: " + date);
            e.printStackTrace();
        }

        // ✅ Assertion block
        try {
            WebElement input = driver.findElement(assertLocator);
            String selectedValue = input.getAttribute("value").trim();

            DateTimeFormatter formatter = DateUtils.getFormatterForContext(context);
            String expectedValue = date.format(formatter).trim();

            System.out.println("Selected Date is: '" + selectedValue + "'");
            System.out.println("Expected Date is: '" + expectedValue + "'");

            Assert.assertEquals(selectedValue, expectedValue, "❌ Selected date mismatch");
        } catch (Exception e) {
            System.out.println("❌ Assertion failed: Unable to locate or validate date input");
            e.printStackTrace();
            Assert.fail("Assertion failed due to missing or incorrect input field");
        }
    }

    /**
     * Selects a date using context-driven locator and format.
     */
    public static void selectDate(WebDriver driver, TestContext context, LocalDate date) {
        By locator = DatePickerLocatorMapper.getDatePickerLocator(context);
        selectDate(driver, locator, locator, date, context);
    }

    /**
     * Convenience method for Forms page.
     */
    public static void selectDateForForms(WebDriver driver, LocalDate date) {
        selectDate(driver, TestContext.FORMS, date);
    }

    /**
     * Convenience method for Widgets > Date Picker page.
     */
    public static void selectDateForWidgets(WebDriver driver, LocalDate date) {
        selectDate(driver, TestContext.DATE_PICKER, date);
    }
}