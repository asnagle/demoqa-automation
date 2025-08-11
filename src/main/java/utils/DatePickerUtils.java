package utils;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class DatePickerUtils {

    private WebDriver driver;

    public DatePickerUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDate(String dobStr) {
        try {
            String[] parts = dobStr.split("-");
            String day = parts[0];
            String month = DateUtils.getMonthName(parts[1]);
            String year = parts[2];

            driver.findElement(By.id("dateOfBirthInput")).click();

            WebElement monthDropdown = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker__month-select")));
            new Select(monthDropdown).selectByVisibleText(month);

            WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
            new Select(yearDropdown).selectByVisibleText(year);

            String paddedDay = String.format("%02d", Integer.parseInt(day));
            String daySelector = String.format("div.react-datepicker__day--0%s", paddedDay);
            WebElement dayElement = driver.findElement(By.cssSelector(daySelector));

            if (dayElement.isDisplayed() && dayElement.isEnabled()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dayElement);
                System.out.println("✅ Date selected: " + dobStr);
            } else {
                System.out.println("⚠️ Day element not interactable: " + paddedDay);
            }

        } catch (Exception e) {
            System.out.println("❌ Failed to select date: " + dobStr);
            e.printStackTrace();
        }
    }
}
