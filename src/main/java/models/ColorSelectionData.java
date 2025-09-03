package models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import customAnnotations.ColumnMapping;

public class ColorSelectionData {

	@ColumnMapping(name = "SearchFor")
	private String searchFor;

	@ColumnMapping(name = "ExpectedColors")
	private String expectedColorsRaw;

    // Getter for search character
    public String getSearchFor() {
        return searchFor;
    }

    // Setter for search character
    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor != null ? searchFor.trim() : null;
    }

    // Getter for expected colors as List<String>
    public List<String> getExpectedColors() {
        if (expectedColorsRaw == null || expectedColorsRaw.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(expectedColorsRaw.split(","))
                     .map(String::trim)
                     .filter(s -> !s.isEmpty())
                     .collect(Collectors.toList());
    }

    // Setter for raw expected colors string
    public void setExpectedColorsRaw(String expectedColorsRaw) {
        this.expectedColorsRaw = expectedColorsRaw;
    }

    // Optional: for logging and debugging
    @Override
    public String toString() {
        return "ColorSelectionData{" +
               "searchFor='" + searchFor + '\'' +
               ", expectedColors=" + getExpectedColors() +
               '}';
    }

    // Optional: sanity check
    public boolean isValid() {
        return searchFor != null && !searchFor.trim().isEmpty();
    }
}