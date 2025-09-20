package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
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
    
    public static void selectDateTime(WebDriver driver, By inputLocator, LocalDateTime dateTime, Logger demoqaLog) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 1: Open calendar
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
            JSclick.scrollAndClick(driver, inputField, demoqaLog, "DateTime Input");
            CalendarUtils.assertCalendarOpened(driver, demoqaLog);
            Thread.sleep(200);

            // Step 2: Pick Year → Month → Day → Time
            selectYear(driver, dateTime.getYear(), demoqaLog);
            Thread.sleep(3000);
            selectMonthFromDropdown(driver, dateTime.getMonth(), demoqaLog);
            selectDay(driver, dateTime.getDayOfMonth(), demoqaLog);
            selectTime(driver, dateTime, demoqaLog);

            // Step 3: Verify
            WebElement input = driver.findElement(By.id("dateAndTimePickerInput"));
            String actual = input.getAttribute("value");
            String expected = dateTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a", Locale.ENGLISH));

            demoqaLog.info("Selected DateTime: " + actual);
            demoqaLog.info("Expected DateTime: " + expected);
            Assert.assertEquals(actual, expected, "❌ DateTime mismatch!");

        } catch (Exception e) {
            demoqaLog.error("❌ Failed to select DateTime " + dateTime, e);
            throw new RuntimeException(e);
        }
    }

    // ------------------------
    // YEAR selection
    // ------------------------
    public static void selectYear(WebDriver driver, int targetYear, Logger demoqaLog) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement trigger = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".react-datepicker__year-read-view")));
        JSclick.scrollAndClick(driver, trigger, demoqaLog, "Year Dropdown Trigger");
        Thread.sleep(150);

        int maxScrolls = 40;
        for (int attempt = 0; attempt < maxScrolls; attempt++) {

            List<WebElement> yearOptions = driver.findElements(By.cssSelector(".react-datepicker__year-option"));

            // Check for direct match
            for (WebElement option : yearOptions) {
                try {
                    String y = option.getText().trim();
                    if (y.equals(String.valueOf(targetYear))) {
                        JSclick.scrollAndClick(driver, option, demoqaLog, "Year " + targetYear);
                        demoqaLog.info("✅ Year selected: " + targetYear);
                        return;
                    }
                } catch (StaleElementReferenceException ignore) {}
            }

            // Determine visible range
            int[] range = getVisibleYearRange(yearOptions);
            if (targetYear < range[0]) {
                clickYearArrow(driver,
                        ".react-datepicker__navigation.react-datepicker__navigation--years.react-datepicker__navigation--years-previous",
                        demoqaLog, "years-previous");
            } else if (targetYear > range[1]) {
                clickYearArrow(driver,
                        ".react-datepicker__navigation.react-datepicker__navigation--years.react-datepicker__navigation--years-next",
                        demoqaLog, "years-next");
            } else {
                demoqaLog.info("ℹ️ Target year within visible range but not clickable yet. Retrying...");
            }

            Thread.sleep(200);
        }

        throw new RuntimeException("❌ Year not found after scrolling attempts: " + targetYear);
    }

    private static boolean clickYearArrow(WebDriver driver, String cssSelector, Logger log, String label) {
        try {
            List<WebElement> arrows = driver.findElements(By.cssSelector(cssSelector));
            for (WebElement arrow : arrows) {
                if (arrow.isDisplayed() && arrow.isEnabled()) {
                    JSclick.scrollAndClick(driver, arrow, log, label);
                    return true;
                }
            }
            log.warn("⚠️ No visible/usable arrow found for selector: " + cssSelector);
        } catch (Exception e) {
            log.error("❌ Error clicking arrow [" + cssSelector + "]: " + e.getMessage());
        }
        return false;
    }

    private static int[] getVisibleYearRange(List<WebElement> yearOptions) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (WebElement opt : yearOptions) {
            try {
                String txt = opt.getText().trim();
                if (txt.isEmpty()) continue;
                int y = Integer.parseInt(txt);
                if (y < min) min = y;
                if (y > max) max = y;
            } catch (Exception ignored) {}
        }
        return new int[]{min, max};
    }

    // ------------------------
    // MONTH selection
    // ------------------------
    public static void selectMonthFromDropdown(WebDriver driver, Month targetMonth, Logger demoqaLog) {
//    	WebElement monthTrigger = wait.until(ExpectedConditions.elementToBeClickable(
//    		    By.cssSelector(".react-datepicker__month-read-view"))); // or whatever triggers the dropdown
//    		JSclick.scrollAndClick(driver, monthTrigger, demoqaLog, "Month Dropdown Trigger");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement monthtrigger = wait.until(ExpectedConditions.elementToBeClickable(
        		By.cssSelector(".react-datepicker__month-read-view")));
        JSclick.scrollAndClick(driver, monthtrigger, demoqaLog, "Month Dropdown Trigger");
        
        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("react-datepicker__month-dropdown")));
        JSclick.scrollAndClick(driver, monthDropdown);

        List<WebElement> monthOptions = monthDropdown.findElements(By.className("react-datepicker__month-option"));

        String targetMonthName = targetMonth.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        for (WebElement option : monthOptions) {
            String optionText = option.getText().trim();
            demoqaLog.debug("🔍 Checking month option: " + optionText);

            if (optionText.equalsIgnoreCase(targetMonthName)) {
                demoqaLog.info("✅ Selecting month: " + optionText);
                option.click();
                return;
            }
        }

        throw new RuntimeException("❌ Month not found in dropdown: " + targetMonthName);
    }

    // ------------------------
    // DAY selection
    // ------------------------
    public static void selectDay(WebDriver driver, int day, Logger demoqaLog) {
        String padded = String.format("%02d", day);
        String selector = ".react-datepicker__day--0" + padded + ":not(.react-datepicker__day--outside-month)";
        List<WebElement> days = driver.findElements(By.cssSelector(selector));
        for (WebElement d : days) {
            if (d.isDisplayed() && d.isEnabled()) {
                JSclick.scrollAndClick(driver, d, demoqaLog, "Day " + day);
                demoqaLog.info("✅ Day selected: " + day);
                return;
            }
        }
        throw new RuntimeException("❌ Day not found: " + day);
    }

    // ------------------------
    // TIME selection (with DataSanitizer)
    // ------------------------
    public static void selectTime(WebDriver driver, LocalDateTime dateTime, Logger demoqaLog) {
        LocalTime target = dateTime.toLocalTime();
        int targetHour = target.getHour();
        int targetMinute = target.getMinute();

        int maxScrolls = 30;
        WebElement timeList = null;
        try {
            timeList = driver.findElement(By.cssSelector(".react-datepicker__time-list"));
        } catch (Exception ignore) {}

        for (int attempt = 0; attempt < maxScrolls; attempt++) {
            List<WebElement> timeOptions = driver.findElements(By.cssSelector(".react-datepicker__time-list-item"));
            for (WebElement opt : timeOptions) {
                try {
                    String txt = opt.getText().trim();
                    Optional<LocalTime> parsed = DataSanitizer.sanitizeTime(txt, "TimeOption", "DatePickerUtils");
                    if (!parsed.isPresent()) continue;
                    LocalTime optTime = parsed.get();
                    if (optTime.getHour() == targetHour && optTime.getMinute() == targetMinute) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opt);
                        JSclick.scrollAndClick(driver, opt, demoqaLog, "Time " + txt);
                        demoqaLog.info("✅ Time selected: " + txt);
                        return;
                    }
                } catch (StaleElementReferenceException ignored) {}
            }

            if (timeList != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += 200;", timeList);
            }
        }

        throw new RuntimeException("❌ Time not found: " + dateTime.format(DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)));
    }
    
    public static void selectDateTime(WebDriver driver, TestContext context, LocalDateTime dateTime, Logger demoqaLog) {
        By inputLocator = DatePickerLocatorMapper.getDatePickerLocator(context);
        selectDateTime(driver, inputLocator, dateTime, demoqaLog);
    }

}