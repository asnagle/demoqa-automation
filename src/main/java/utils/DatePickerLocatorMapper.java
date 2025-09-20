package utils;

import enums.TestContext;
import org.openqa.selenium.By;

public class DatePickerLocatorMapper {

	public static By getDatePickerLocator(TestContext context) {
	    return switch (context) {
	        case FORMS -> By.id("dateOfBirthInput");                  // 🎯 Form DOB input
	        case DATE_PICKER -> By.id("datePickerMonthYearInput");    // 🎯 "Select Date"
	        case DATE_TIME_PICKER -> By.id("dateAndTimePickerInput"); // 🎯 "Date And Time"
	        default -> throw new IllegalArgumentException("Unhandled TestContext: " + context);
	    };
	}
}
