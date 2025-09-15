package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class DatePickerUtils {

    /**
     * Selects a date in a React-based date picker widget.
     * @param driver WebDriver instance
     * @param inputLocator Locator for the date input field
     * @param date LocalDate to select
     */
    public static void selectDate(WebDriver driver, By inputLocator, LocalDate date) {
        try {
            String day = String.valueOf(date.getDayOfMonth());
            String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String year = String.valueOf(date.getYear());

            // Step 1: Open the calendar
            WebElement inputField = driver.findElement(inputLocator);
            inputField.click();

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
//	    Assert Selection is Correct
	    
	    WebElement input = driver.findElement(By.id("datePickerMonthYearInput"));
	    String selectedValue = input.getAttribute("value");
	    String expectedValue = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	    demoqaLog.info("Selected Date is: " + selectedValue);
	    System.out.println("Selected Date is: " + selectedValue);
	    demoqaLog.info("Expected Date is: " + expectedValue);
	    System.out.println("Expected Date is: " + expectedValue);
	    
	    Assert.assertEquals(selectedValue, expectedValue, "❌ Selected date mismatch");
    }

    /**
     * Convenience method for Forms page (uses default locator).
     * @param driver WebDriver instance
     * @param date LocalDate to select
     */
    public static void selectDateForForms(WebDriver driver, LocalDate date) {
        selectDate(driver, By.id("dateOfBirthInput"), date);
    }
}