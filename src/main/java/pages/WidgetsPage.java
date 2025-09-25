package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import base.demoqaBase;
import models.ColorSelectionData;
import models.WebTableUser;
import utils.ClickHandler;
import utils.ExcelUtils;
import utils.JSclick;
import utils.PageLoadHandler;
import utils.SliderUtils;
import utils.waitForElement;
//import utils.CalendarUtils;


public class WidgetsPage extends demoqaBase {

	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'body-height')]/div[contains(@class,'container playgound-body')]/div[contains(@class,'row')]/div[contains(@class,'col-md-3')]/div[contains(@class,'left-pannel')]/div[contains(@class,'accordion')]/div[4]/span[1]/div[1]")
	WebElement PageText;

	@FindBy(xpath = "//div[@class='col-12 mt-4 col-md-6']")
	WebElement WidgetPg;

	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-0']")
	WebElement Accordian;

	@FindBy(xpath = "//h1[normalize-space()='Accordian']")
	WebElement AccordianPgTitle;

	@FindBy(xpath = "//div[@id='section1Heading']")
	WebElement Whatis;

	@FindBy(xpath = "//p[contains(text(),'Lorem Ipsum is simply dummy text of the printing a')]")
	WebElement WhatisContent;

	@FindBy(xpath = "//div[@id='section2Heading']")
	WebElement WhereDoesIt;

	@FindBy(xpath = "//p[contains(text(),'Contrary to popular belief, Lorem Ipsum is not sim')]")
	WebElement WhereDoesContent;

	@FindBy(xpath = "//div[@id='section3Heading']")
	WebElement WhyDoWe;

	@FindBy(xpath = "//p[contains(text(),'It is a long established fact that a reader will b')]")
	WebElement WhyDoWeContent;

	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-1']")
	WebElement AutoComplete;

	@FindBy(xpath = "//span[normalize-space()='Auto Complete']")
	WebElement AutoCompletePgTitle;

//	@FindBy(xpath = "//div[@class='auto-complete__value-container auto-complete__value-container--is-multi css-1hwfws3']")
	@FindBy(xpath = "//div[@id='autoCompleteMultipleContainer']//input")
	WebElement AutoCompleteMulti;
	
	@FindBy(xpath = "//span[normalize-space()='Type multiple color names']")
	WebElement MultiSearchBoxTitle;

	@FindBy(id = "autoCompleteSingleInput")
	WebElement AutoCompleteSingle;
	
	@FindBy(xpath = "//span[normalize-space()='Type single color name']")
	WebElement SingleSearchBoxTitle;
	
	@FindBy(xpath = "//div[@class='auto-complete__single-value css-1uccc91-singleValue']\r\n"
			+ "")
	WebElement AutoCompSingleResults;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-2']")
	WebElement DatePicker;
	
	@FindBy(xpath = "//h1[normalize-space()='Date Picker']")
	WebElement DatePickerTitle;
	
	@FindBy(id = "datePickerMonthYearInput")
	WebElement datePickerInput;
	
	@FindBy(id = "dateAndTimePickerInput")
	WebElement dateAndTimePickerInput;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-3']")
	WebElement slider;
	
	@FindBy(xpath = "//h1[normalize-space()='Slider']")
	WebElement sliderPgTitle;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-4']")
	WebElement ProgressBar;
	
	@FindBy(xpath = "//h1[normalize-space()='Progress Bar']")
	WebElement ProgressBarTitle;

	
	

	private WebDriverWait wait;

	public WidgetsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		PageFactory.initElements(driver, this);
	}

	public void accessWidgets() {

		demoqaLog.info("Accessing Widgets Card...");
		homePage homePage = new homePage(driver);
		System.out.println("Declared Instance WebDriverWait: " + wait);
		homePage.clickWidgetsCard();
		System.out.println("Title of this Page is: " + driver.getTitle());
		System.out.println("You are Accessing: " + PageText.getText());
		String widgetpage = WidgetPg.getText();
		demoqaLog.info("Widgets: " + widgetpage);

		Assert.assertEquals("Please select an item from left to start practice.", widgetpage);
	}

	public void ClickAccordian() {
		demoqaLog.info("Clicking on Widgets|Accordian...");
		Accordian.click();
		String accordianPage = AccordianPgTitle.getText();
		System.out.println("You are now Accessing: " + accordianPage);
		demoqaLog.info("You are now Accessing: " + accordianPage);

		Assert.assertEquals("Accordian", accordianPage);
	}

	public void AccessWhatis() {
		demoqaLog.info("Clicking on Widgets|Accordian|What is Lorem Ipsum?...");
		WebElement whatis = driver.findElement(By.xpath("//div[@id='section1Heading']"));
		waitForElement.waitAndClick(driver, whatis);
		ClickHandler.smartClick(driver, Whatis);
		demoqaLog.info("Clicked on What is Lorem Ipsum...");
		String whatiscontent = WhatisContent.getText();
		System.out.println("What is Lorem Ipsum?: " + whatiscontent);
		demoqaLog.info("You are now Accessing: " + whatiscontent);

		Assert.assertEquals(true, whatiscontent.contains("Lorem Ipsum is simply dummy text"));
	}

	public void AccessWhereDoesIt() {
	    demoqaLog.info("Clicking on Widgets|Accordian|Where does it come from?...");
	    ClickHandler.smartClick(driver, WhereDoesIt);
	    demoqaLog.info("Clicked on Where does it come from?...");

	    // Wait for visibility before accessing content
	    if (waitForElement.isElementVisible(driver, WhereDoesContent)) {
	        String wheredoescontent = WhereDoesContent.getText();
	        demoqaLog.info("You are now Accessing: {}", wheredoescontent);
	        System.out.println("Where does it come from?: " + wheredoescontent);

	        Assert.assertTrue(
	            wheredoescontent.contains("Contrary to popular belief, Lorem Ipsum is not sim"),
	            "Expected accordion content not found or incomplete"
	        );
	    } else {
	        Assert.fail("❌ 'WhereDoesContent' was not visible within timeout");
	    }
	}

	public void AccessWhyDoWe() {
	    demoqaLog.info("Clicking on Widgets|Accordian|Why do we use it?...");
	    ClickHandler.smartClick(driver, WhyDoWe);
	    demoqaLog.info("Clicked on Why do we use it?...");

	    // Wait for visibility before accessing text
	    if (waitForElement.isElementVisible(driver, WhyDoWeContent)) {
	        String whydowecontent = WhyDoWeContent.getText();
	        System.out.println("Why do we use it?: " + whydowecontent);
	        demoqaLog.info("You are now Accessing: " + whydowecontent);

	        Assert.assertTrue(
	            whydowecontent.contains("It is a long established fact that a reader will"),
	            "Expected content not found in accordion section"
	        );
	    } else {
	        Assert.fail("WhyDoWeContent was not visible within timeout");
	    }
	}

	public void ClickAutoComplete() {
		demoqaLog.info("Clicking on Widgets|AutoComplete...");
		AutoComplete.click();
		String autocompletePage = AutoCompletePgTitle.getText();
		System.out.println("You are now Accessing: " + autocompletePage);
		demoqaLog.info("You are now Accessing: " + autocompletePage);

		Assert.assertEquals("Auto Complete", autocompletePage);
	}

	public void searchAutoCompleteMulti(String searchChar, List<String> expectedColors) {
	    demoqaLog.info("Clicking on Widgets | Auto Complete | Type multiple color names...");
	    System.out.println("Searching Colors using: " + searchChar);
	    System.out.println("Expected Color to be selected is/are: " + expectedColors);

	    String autocompletePage = AutoCompletePgTitle.getText();
	    demoqaLog.info("You are now Accessing: {}", autocompletePage);

	    for (String expectedColor : expectedColors) {
	        try {
	            // Re-fetch input box before each interaction
	            WebElement inputBox = wait.until(ExpectedConditions.elementToBeClickable(AutoCompleteMulti));
	            inputBox.click();
	            inputBox.clear();
	            inputBox.sendKeys(searchChar);

	            // Wait for dropdown to populate
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("auto-complete__menu-list")));
	            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'auto-complete__option')]")));

	            // Re-fetch color options to avoid stale references
	            List<WebElement> colorOptions = driver.findElements(By.xpath("//div[contains(@class,'auto-complete__option')]"));

	            boolean matched = false;
	            for (WebElement option : colorOptions) {
	                String optionText = option.getText().trim();
	                demoqaLog.info("Available Color: {}", optionText);

	                if (optionText.equalsIgnoreCase(expectedColor)) {
	                    ClickHandler.smartClick(driver, option);
	                    demoqaLog.info("✅ Selected Color: {}", expectedColor);
	                    matched = true;
	                    break;
	                }
	            }

	            if (!matched) {
	                demoqaLog.warn("❌ Color '{}' not found for search '{}'", expectedColor, searchChar);
	            }

	        } catch (StaleElementReferenceException sere) {
	            demoqaLog.warn("⚠️ Stale element encountered for '{}'. Retrying...", expectedColor);
	            retryColorSelection(searchChar, expectedColor);
	        } catch (Exception e) {
	            demoqaLog.error("❌ Error selecting color '{}': {}", expectedColor, e.getMessage());
	        }
	    }
	    

	    validateSelectedColors(expectedColors, searchChar);
	}
	
	public void retryColorSelection(String searchChar, String expectedColor) {
	    try {
	        WebElement inputBox = wait.until(ExpectedConditions.elementToBeClickable(AutoCompleteMulti));
	        inputBox.click();
	        inputBox.clear();
	        inputBox.sendKeys(searchChar);

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("auto-complete__menu-list")));
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'auto-complete__option')]")));

	        List<WebElement> colorOptions = driver.findElements(By.xpath("//div[contains(@class,'auto-complete__option')]"));

	        for (WebElement option : colorOptions) {
	            if (option.getText().trim().equalsIgnoreCase(expectedColor)) {
	                ClickHandler.smartClick(driver, option);
	                demoqaLog.info("🔁 Retried and selected color: {}", expectedColor);
	                return;
	            }
	        }

	        demoqaLog.warn("❌ Retry failed: Color '{}' not found for search '{}'", expectedColor, searchChar);

	    } catch (Exception e) {
	        demoqaLog.error("❌ Retry exception for '{}': {}", expectedColor, e.getMessage());
	    }
	}
	
	public void validateSelectedColors(List<String> expectedColors, String searchChar) {
	    List<WebElement> selectedTags = driver.findElements(By.cssSelector(".auto-complete__multi-value__label"));
	    List<String> selectedColors = selectedTags.stream().map(WebElement::getText).collect(Collectors.toList());

	    demoqaLog.info("🎯 Final selected colors: {}", selectedColors);

	    if (!selectedColors.containsAll(expectedColors)) {
	        demoqaLog.warn("⚠️ Mismatch in selected vs expected colors for '{}'", searchChar);
	    }
	}
	
	public void selectAndRemoveColors(String searchChar, ExtentTest testRep) throws IOException {
	    String filePath = System.getProperty("user.dir") + "/TestData/ColorSelection.xlsx";

	    // ✅ Phase 1: Selection from Sheet1
	    Object[][] selectionData = ExcelUtils.getMappedData(filePath, "Sheet1", ColorSelectionData.class);
	    List<String> successfullySelected = new ArrayList<>();

	    for (Object[] row : selectionData) {
	        ColorSelectionData entry = (ColorSelectionData) row[0];
	        if (searchChar.equalsIgnoreCase(entry.getSearchFor())) {
	            List<String> expectedColors = entry.getExpectedColors();
	            demoqaLog.info("🎨 Selecting colors for '{}': {}", searchChar, expectedColors);
	            testRep.info("Selecting colors for '" + searchChar + "': " + expectedColors);

	            for (String color : expectedColors) {
	                if (selectColorMulti(searchChar, color)) {
	                    successfullySelected.add(color);
	                    testRep.pass("Selected color: " + color);
	                } else {
	                    testRep.warning("Color '" + color + "' not found in dropdown for '" + searchChar + "'");
	                }
	            }
	            break;
	        }
	    }

	    if (successfullySelected.isEmpty()) {
	        demoqaLog.warn("⚠️ No colors selected for '{}'", searchChar);
	        testRep.warning("No colors selected for '" + searchChar + "'");
	    }

	    // ✅ Phase 2: Removal from Sheet2
	    Object[][] removalData = ExcelUtils.getMappedData(filePath, "Sheet2", ColorSelectionData.class);

	    for (Object[] row : removalData) {
	        ColorSelectionData entry = (ColorSelectionData) row[0];
	        if (searchChar.equalsIgnoreCase(entry.getSearchFor())) {
	            List<String> colorsToRemove = entry.getExpectedColors();
	            demoqaLog.info("🗑️ Attempting to remove colors for '{}': {}", searchChar, colorsToRemove);
	            testRep.info("Attempting to remove colors for '" + searchChar + "': " + colorsToRemove);

	            List<String> successfullyRemoved = new ArrayList<>();

	            for (String color : colorsToRemove) {
	                if (successfullySelected.contains(color)) {
	                    if (removeColorFromAutoComplete(color)) {
	                        successfullyRemoved.add(color);
	                        testRep.pass("Removed color: " + color);
	                    } else {
	                        testRep.warning("Failed to remove color: " + color);
	                    }
	                } else {
	                    testRep.warning("Color '" + color + "' was not selected and cannot be removed.");
	                }
	            }

	            // ✅ Assertion Phase
	            if (!successfullyRemoved.isEmpty()) {
	                assertColorsRemoved(successfullyRemoved);
	            } else {
	                demoqaLog.warn("⚠️ No colors were successfully removed for '{}'", searchChar);
	                testRep.warning("No colors were successfully removed for '" + searchChar + "'");
	            }

	            return;
	        }
	    }

	    demoqaLog.warn("⚠️ No removal data found for '{}' in Sheet2", searchChar);
	    testRep.warning("No removal data found for '" + searchChar + "' in Sheet2");
	}
	public boolean selectColorMulti(String searchChar, String color) {
	    try {
	        waitForElement.waitAndClick(driver, AutoCompleteMulti);
	        AutoCompleteMulti.clear();
	        AutoCompleteMulti.sendKeys(searchChar);

	        By dropdownOptionLocator = By.cssSelector(".auto-complete__option");

	        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(dropdownOptionLocator, 0));

	        List<WebElement> dropdownOptions = driver.findElements(dropdownOptionLocator);

	        for (WebElement option : dropdownOptions) {
	            String optionText = option.getText().trim();
	            if (optionText.equalsIgnoreCase(color.trim())) {
	                ClickHandler.smartClick(driver, option);
	                demoqaLog.info("✅ Selected color '{}' for '{}'", color, searchChar);
	                return true;
	            }
	        }

	        demoqaLog.warn("⚠️ Color '{}' not found in dropdown for '{}'", color, searchChar);
	        return false;

	    } catch (Exception e) {
	        demoqaLog.error("❌ Error selecting color '{}': {}", color, e.getMessage());
	        return false;
	    }
	}
	
	public boolean removeColorFromAutoComplete(String colorToRemove) {
	    try {
	        List<WebElement> selectedTags = driver.findElements(By.cssSelector(".css-1rhbuit-multiValue"));

	        for (WebElement tag : selectedTags) {
	            WebElement label = tag.findElement(By.cssSelector(".css-12jo7m5"));
	            String tagText = label.getText().trim();

	            if (tagText.equalsIgnoreCase(colorToRemove.trim())) {
	                WebElement removeButton = tag.findElement(By.cssSelector(".css-xb97g8"));
	                removeButton.click();
	                demoqaLog.info("🗑️ Removed color: {}", colorToRemove);
	                return true;
	            }
	        }

	        demoqaLog.warn("⚠️ Color '{}' not found in selected tags", colorToRemove);
	        return false;

	    } catch (Exception e) {
	        demoqaLog.error("❌ Error removing color '{}': {}", colorToRemove, e.getMessage());
	        return false;
	    }
	}
	
	public void assertColorsRemoved(List<String> colors) {
	    for (String color : colors) {
	        By colorLocator = By.xpath("//div[contains(@class,'auto-complete__multi-value__label') and text()='" + color + "']");

	        boolean disappeared = waitForElement.waitUntilInvisible(driver, colorLocator);
	        Assert.assertTrue(disappeared, "❌ Color '" + color + "' did not disappear within timeout.");

	        boolean isRemoved = driver.findElements(colorLocator).isEmpty();
	        Assert.assertTrue(isRemoved, "❌ Color '" + color + "' was not removed from selected tags.");

	        demoqaLog.info("✅ Assertion passed: Color '{}' successfully removed.", color);
	    }
	}
	
	public void singleSearchAdd(String searchChar, String SelectColor) {
		demoqaLog.info("Clicking on Widgets | Auto Complete | Type single color name...");
	    System.out.println("Searching Colors using: " + searchChar);
	    System.out.println("Expected Color to be selected is/are: " + SelectColor);

	    String autocompletePage = AutoCompletePgTitle.getText();
	    demoqaLog.info("You are now Accessing: {}", autocompletePage);
	    PageLoadHandler.waitUntilLoaded(driver, 30);
	    selectColorSingle(searchChar, SelectColor);
	    
	}
	
	public void selectColorSingle(String searchChar, String SelectColor) {
	    try {
	    	waitForElement.waitUntilInteractable(driver, AutoCompleteSingle);
	    	ClickHandler.smartClick(driver, AutoCompleteSingle);
	    	demoqaLog.info("Clicked on Search Box with Title: " + SingleSearchBoxTitle.getText());
	    	AutoCompleteSingle.clear();
	    	AutoCompleteSingle.sendKeys(searchChar);

	        Thread.sleep(3000); // Allow dropdown to populate

	        List<WebElement> options = driver.findElements(By.cssSelector(".auto-complete__option"));
	        boolean found = false;

	        for (WebElement option : options) {
	            String text = option.getText().trim();
	            if (text.equalsIgnoreCase(SelectColor.trim())) {
	                option.click();
	                demoqaLog.info("✅ Selected color '{}'", text);
	                found = true;
	                break;
	            }
	        }

	        Assert.assertTrue(found, "❌ Color '" + SelectColor + "' not found in dropdown");
	        demoqaLog.info("Asserted Selected Color is: " + AutoCompSingleResults.getText());

	    } catch (Exception e) {
	        demoqaLog.error("❌ Failed to select color '{}': {}", SelectColor, e.getMessage(), e);
	        throw new RuntimeException("Color selection failed", e);
	    }
	}
	
	public void removeColorSingle(String expectedColor) {
	    String currentColor = AutoCompSingleResults.getText();
	    demoqaLog.info("🔍 Checking selected color. Current value: '{}'", currentColor);

	    if (!currentColor.equalsIgnoreCase(expectedColor)) {
	        demoqaLog.warn("⚠️ Expected color '{}' not found. Skipping clear operation.", expectedColor);
	        return;
	    }

	    boolean clicked = JSclick.safeClick(driver, AutoCompleteSingle, demoqaLog, "AutoCompleteSingle");

	    if (clicked) {
	        AutoCompleteSingle.sendKeys("  ");
	        demoqaLog.info("✅ Cleared color input for '{}'", expectedColor);
	    } else {
	        demoqaLog.error("🚫 Unable to click 'AutoCompleteSingle'. Color clear skipped.");
	    }
	}
	
	public void ClickDatePicker() {
		demoqaLog.info("Clicking on Widgets|Date Picker...");
		ClickHandler.smartClick(driver, DatePicker);
		String datePickerPage = DatePickerTitle.getText();
		System.out.println("You are now Accessing: " + datePickerPage);
		demoqaLog.info("You are now Accessing: " + datePickerPage);

		Assert.assertEquals("Date Picker", datePickerPage);
	}
	
	public void ClickSlider() {
		demoqaLog.info("Clicking on Widgets|Slider...");
//		slider.click();
		ClickHandler.smartClick(driver, slider);
		String sliderpageTitle = sliderPgTitle.getText();
		System.out.println("You are now Accessing: " + sliderpageTitle);
		demoqaLog.info("You are now Accessing: " + sliderpageTitle);

		Assert.assertEquals("Slider", sliderpageTitle);
	}
	
	public int moveSliderToAge(WebTableUser user) {
	    By sliderLocator = By.id("sliderValue");
	    SliderUtils sliderUtils = new SliderUtils(driver);

	    int targetAge = user.getAge() / 10;
	    demoqaLog.info("🎯 Target slider value (Age): {}", targetAge);

	    sliderUtils.moveSliderToValueJS(sliderLocator, targetAge);
	    int actualAge = sliderUtils.getSliderValue(sliderLocator);

	    demoqaLog.info("📍 Slider moved to: {}", actualAge);

	    return actualAge;
	}
	
	
}
