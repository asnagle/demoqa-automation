package utils;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utils.LabelAliasRegistry.LABEL_ALIASES;

public class ConfirmationTableParser {

    private static final Logger demoqaLog = LogManager.getLogger(ConfirmationTableParser.class);

    private static final Set<String> KNOWN_STATES = Set.of(
        "NCR", "Delhi", "Uttar Pradesh", "Maharashtra", "Rajasthan", "Haryana", "Karnataka"
    );

    public static Map<String, String> extractValues(WebDriver driver, By tableRowLocator) {
        Map<String, String> values = new LinkedHashMap<>();
        List<WebElement> rows = driver.findElements(tableRowLocator);

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() < 2) continue;

            String rawLabel = extractCellValue(cells.get(0));
            String rawValue = extractCellValue(cells.get(1));
            String normalizedLabel = LABEL_ALIASES.getOrDefault(rawLabel, rawLabel);

            demoqaLog.debug("🔍 Row parsed: rawLabel='{}', normalizedLabel='{}', rawValue='{}'",
                            rawLabel, normalizedLabel, rawValue);

            // Special-case: "State and City"
            if (rawLabel.equalsIgnoreCase("State and City")) {
                Map<String, String> split = splitStateAndCity(rawValue);
                values.put("State", split.get("State"));
                values.put("City", split.get("City"));
                demoqaLog.info("📋 Parsed [State] = '{}', [City] = '{}'",
                               split.get("State"), split.get("City"));
                continue;
            }

            // Special-case: "Student Name"
            if (rawLabel.equalsIgnoreCase("Student Name")) {
                String[] nameParts = rawValue.trim().split("\\s+", 2);
                if (nameParts.length == 2) {
                    values.put("First Name", nameParts[0]);
                    values.put("Last Name", nameParts[1]);
                    demoqaLog.info("📋 Parsed [First Name] = '{}', [Last Name] = '{}'",
                                   nameParts[0], nameParts[1]);
                } else {
                    values.put("First Name", rawValue);
                    demoqaLog.warn("⚠️ Unexpected format for 'Student Name': '{}'", rawValue);
                }
                values.put("Student Name", rawValue);
                continue;
            }

            // Default case: insert normalized label
            values.put(normalizedLabel, rawValue);
            demoqaLog.info("📋 Parsed [{}] = '{}'", normalizedLabel, rawValue);

            if (rawValue.isEmpty()) {
                demoqaLog.warn("⚠️ Empty value for label '{}', original label='{}'",
                               normalizedLabel, rawLabel);
            }
        }

        return values;
    }

    private static String extractCellValue(WebElement cell) {
        String value = cell.getText().trim();

        if (!value.isEmpty()) return value;

        value = cell.getDomProperty("innerText").trim();
        if (!value.isEmpty()) return value;

        String html = cell.getAttribute("innerHTML").trim();
        value = html.replaceAll("<[^>]+>", "").replaceAll("\\s+", " ").trim();

        demoqaLog.debug("🧪 Fallback innerHTML for cell: '{}'", html);
        demoqaLog.debug("🧪 Extracted fallback value: '{}'", value);

        return value;
    }

    private static Map<String, String> splitStateAndCity(String combined) {
        for (String knownState : KNOWN_STATES) {
            if (combined.startsWith(knownState)) {
                String city = combined.substring(knownState.length()).trim();
                return Map.of("State", knownState, "City", city);
            }
        }
        demoqaLog.warn("⚠️ Could not split State and City from: '{}'", combined);
        return Map.of("State", combined, "City", "");
    }
}