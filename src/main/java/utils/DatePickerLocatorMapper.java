package utils;

import enums.TestContext;
import org.openqa.selenium.By;

public class DatePickerLocatorMapper {

    public static By getDatePickerLocator(TestContext context) {
        return switch (context) {
            case FORMS -> By.id("dateOfBirthInput");
            case DATE_PICKER -> By.id("datePickerMonthYearInput");
        };
    }
}