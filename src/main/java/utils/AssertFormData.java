package utils;

import java.util.Map;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.UserFormData;

import static utils.LabelAliasRegistry.LABEL_ALIASES;

public class AssertFormData {

    private static final Logger demoqaLog = LogManager.getLogger(AssertFormData.class);

    /**
     * Core assertion logic: compares expected vs actual values with normalization and alias resolution.
     */
    public static void assertConfirmation(Map<String, String> expectedValues, Map<String, String> actualValues) {
        expectedValues.forEach((expectedLabel, expectedValue) -> {
            String normalizedLabel = LABEL_ALIASES.getOrDefault(expectedLabel, expectedLabel);
            String actualValue = actualValues.getOrDefault(normalizedLabel, "");

            assertFieldMatch(normalizedLabel, expectedValue, actualValue);
        });
    }

    /**
     * Overload for POJO-driven assertion using WebDriver and confirmation table locator.
     */
    public static void assertConfirmation(UserFormData pojo, WebDriver driver, By tableRowLocator, Logger externalLog) {
        Map<String, String> expectedValues = PojoSanitizer.extractSanitizedFields(pojo);
        Map<String, String> actualValues = ConfirmationTableParser.extractValues(driver, tableRowLocator);

        externalLog.info("🧪 Starting confirmation assertion for user: {}", pojo.getFirstName());
        assertConfirmation(expectedValues, actualValues);
    }

    /**
     * Helper method to normalize and assert individual field values.
     */
    private static void assertFieldMatch(String label, String expectedRaw, String actualRaw) {
        // 🧼 Flatten both escaped and actual line breaks
        String expectedFlat = expectedRaw.replace("\\n", " ").replaceAll("\\r?\\n", " ");
        String actualFlat = actualRaw.replace("\\n", " ").replaceAll("\\r?\\n", " ");

        String expected = DataSanitizer.sanitizeField(expectedFlat, label);
        String actual = DataSanitizer.sanitizeField(actualFlat, label);

        demoqaLog.info("🔍 [{}] Raw expected='{}', Raw actual='{}'", label, expectedRaw, actualRaw);
        demoqaLog.info("🔧 [{}] Normalized expected='{}', Normalized actual='{}'", label, expected, actual);

        if (!Objects.equals(expected, actual)) {
            demoqaLog.error("❌ [{}] mismatch: expected='{}', actual='{}'", label, expected, actual);
        } else {
            demoqaLog.info("✅ [{}] matched: '{}'", label, actual);
        }

        Assert.assertEquals(actual, expected, "Mismatch for field: " + label);
    }
}