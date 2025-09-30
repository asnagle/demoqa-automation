package tests;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.demoqaBase;
import customAnnotations.CaptureOnSuccess;
import enums.TestContext;
import models.ColorSelectionData;
import models.UserFormData;
import models.WebTableUser;
import pages.WidgetsPage;
import utils.DataSanitizer;
import utils.DatePickerUtils;
import utils.ExcelUtils;
import utils.PageLoadHandler;
//import utils.ProgressBarUtils;
import utils.extentReportManager;

public class WidgetsTests extends demoqaBase {

	@DataProvider(name = "ColorSelectionData")
	public Object[][] provideColorSelectionData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/TestData/ColorSelection.xlsx";
		return ExcelUtils.getMappedData(filePath, "Sheet1", ColorSelectionData.class);
	}

	@DataProvider(name = "ColorSelectionDataRemove")
	public Object[][] provideColorSelectionDataRemove() throws IOException {
		String filePath = System.getProperty("user.dir") + "/TestData/ColorSelection.xlsx";
		return ExcelUtils.getMappedData(filePath, "Sheet2", ColorSelectionData.class);
	}

	@DataProvider(name = "SingleColorData")
	public Object[][] getTestData() {

		return new Object[][] { { "E", "Green" } };
	}

	@DataProvider(name = "DateSource")
	public Object[][] provideUserFormData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		return ExcelUtils.getMappedData(filePath, "Sheet1", UserFormData.class);
	}

	@DataProvider(name = "WebTableDataSourceSingle")
	public Object[][] getSingleUserData() {
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		String sheetName = "Sheet1";
		WebTableUser user = ExcelUtils.getFirstUserFromExcel(filePath, sheetName);
		return new Object[][] { { user } };
	}

	@DataProvider(name = "WebTableDataSourceAll")
	public Object[][] provideWebTableUserData2() throws IOException {
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		String sheetName = "Sheet1";

		List<WebTableUser> users = ExcelUtils.getMappedList(filePath, sheetName, WebTableUser.class);

		Object[][] data = new Object[users.size()][1];
		for (int i = 0; i < users.size(); i++) {
			data[i][0] = users.get(i);
		}
		return data;
	}

	@Test(priority = 1, description = "Test Widgets...")
	public void accessWidgets() {
		testRep = extentReportManager.createTest("Test Widgets...");
		testRep.info("🧪 Starting test for Widgets");
		demoqaLog.info("🧪 Starting Test Widgets...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		String widgetpage = widgetsPage.accessWidgets();
		
		Assert.assertEquals("Please select an item from left to start practice.", widgetpage);
		
		testRep.pass("✅ Assertion Confirmation Text: " + widgetpage);
		testRep.pass("✅ Test Alerts, Frames & Windows Test Completed...");
		demoqaLog.info("✅ Widgets Test Completed...");
	}

	@Test(priority = 2, description = "Test Widgets|Accordian...")
	public void Accordian() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian...");
		testRep.info("🧪 Starting test for Widgets|Accordian");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String accordianPage = widgetsPage.ClickAccordian();
		
		Assert.assertEquals("Accordian", accordianPage);
		
		testRep.pass("✅ Assertion Confirmation Text: " + accordianPage);
		testRep.pass("✅ Test Widgets|Accordian Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian Test Completed...");
	}

	@Test(priority = 3, description = "Test Widgets|Accordian|What is Lorem Ipsum?...")
	public void AccessWhatis() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian|What is Lorem Ipsum?...");
		testRep.info("🧪 Starting test for Widgets|Accordian|What is Lorem Ipsum?");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian|What is Lorem Ipsum?...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAccordian();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		String whatiscontent = widgetsPage.AccessWhatis();
		
		Assert.assertEquals(true,
				whatiscontent.contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry"));
		testRep.pass("✅ Assertion Confirmation Text: " + whatiscontent);
		testRep.pass("✅ Test Widgets|Accordian|What is Lorem Ipsum? Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian|What is Lorem Ipsum? Test Completed...");
	}

	@Test(priority = 4, description = "Test Widgets|Accordian|Where does it come from?...")
	public void AccessWhereDoes() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian|Where does it come from?...");
		testRep.info("🧪 Starting test for Widgets|Accordian|Where does it come from?");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian|Where does it come from?...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAccordian();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		widgetsPage.AccessWhereDoesIt();
		testRep.pass("✅ Test Widgets|Accordian|Where does it come from? Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian|Where does it come from? Test Completed...");
	}

	@Test(priority = 5, description = "Test Widgets|Accordian|Why do we use it?...")
	public void AccessWhyDoWe() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian|Why do we use it?...");
		testRep.info("🧪 Starting test for Widgets|Accordian|Why do we use it?");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian|Why do we use it?...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAccordian();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		widgetsPage.AccessWhyDoWe();
		testRep.pass("✅ Test Widgets|Accordian|Why do we use it? Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian|Why do we use it? Test Completed...");
	}

	@Test(priority = 6, description = "Test Widgets|Auto Complete...")
	public void AutoComplete() {
		testRep = extentReportManager.createTest("Test Widgets|Auto Complete...");
		testRep.info("🧪 Starting test for Widgets|Auto Complete");
		demoqaLog.info("🧪 Starting Test Widgets|Auto Complete...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		String autocompletePage = widgetsPage.ClickAutoComplete();
		
		Assert.assertEquals("Auto Complete", autocompletePage);
		testRep.pass("✅ Assertion Confirmation Text: " + autocompletePage);
		testRep.pass("✅ Test Widgets|Auto Complete Test Completed...");
		demoqaLog.info("✅ Widgets|Auto Complete Test Completed...");
	}

	@Test(priority = 7, dataProvider = "ColorSelectionData", description = "Test Widgets|Auto Complete using input from Spreadsheet...")
	public void AutoCompleteSearch(ColorSelectionData data) {
		testRep = extentReportManager.createTest("Test Widgets|Auto Complete...");
		testRep.info("🧪 Starting test for Widgets|Auto Complete");
		demoqaLog.info("🧪 Starting Test Widgets|Auto Complete...");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAutoComplete();
		if (data == null) {
			demoqaLog.error("❌ ColorSelectionData is null. Cannot proceed.");
			testRep.fail("ColorSelectionData is null.");
			return;
		}

		String searchChar = data.getSearchFor();
		System.out.println("Searching Colors using: " + searchChar);

		List<String> expectedColors = data.getExpectedColors();
		System.out.println("Expected Color to be selected is/are: " + expectedColors);

		testRep.info("🔍 Running test for search character: " + searchChar);
		demoqaLog.info("🔍 Running test for search character: {}", searchChar);
		PageLoadHandler.waitUntilLoaded(driver, 30);

		widgetsPage.searchAutoCompleteMulti(searchChar, expectedColors);

		testRep.pass("✅ Test Widgets|Auto Complete Test Completed...");
		demoqaLog.info("✅ Widgets|Auto Complete Test Completed...");
	}

	@Test(priority = 8, dataProvider = "ColorSelectionDataRemove", description = "Test Widgets|Auto Complete - Select & Remove Flow using input from Spreadsheet...")
	public void AutoCompleteSearchRemove(ColorSelectionData removalData) throws IOException {
		testRep = extentReportManager.createTest("Test Widgets|Auto Complete - Select & Remove Flow");
		demoqaLog.info("🧪 Starting Test Widgets|Auto Complete - Select & Remove Flow");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAutoComplete();

		if (removalData == null || !removalData.isValid()) {
			demoqaLog.error("❌ Removal data is null or invalid. Cannot proceed.");
			testRep.fail("Removal data is null or invalid.");
			return;
		}

		String searchChar = removalData.getSearchFor();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		widgetsPage.selectAndRemoveColors(searchChar, testRep);

		testRep.pass("✅ Widgets|Auto Complete Select & Remove Flow Completed");
		demoqaLog.info("✅ Auto Complete Select & Remove Flow completed for '{}'", searchChar);
	}

	@Test(priority = 9, dataProvider = "SingleColorData", description = "Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Search & Select using input from Spreadsheet...")
	public void AutoCompleteSingle(String searchChar, String SelectColor) {
		testRep = extentReportManager.createTest(
				"Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Search & Select");
		demoqaLog.info(
				"🧪 Starting Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Search & Select");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAutoComplete();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		widgetsPage.singleSearchAdd(searchChar, SelectColor);

		testRep.pass(
				"✅ Widgets|Auto Complete - Select & Remove Flow|Type single color name - Search & Select Completed");
		demoqaLog.info(
				"✅ Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Search & Select Completed");
	}

	@Test(priority = 10, dataProvider = "SingleColorData", description = "Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Remove Selection using input from Spreadsheet...")
	public void SingleAddRemove(String searchChar, String SelectColor) {
		testRep = extentReportManager.createTest(
				"Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Remove Selection");
		demoqaLog
				.info("🧪 Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Remove Selection");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAutoComplete();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		widgetsPage.singleSearchAdd(searchChar, SelectColor);

		widgetsPage.removeColorSingle(SelectColor);

		testRep.pass(
				"✅ Widgets|Auto Complete - Select & Remove Flow|Type single color name - Remove Selection Completed");
		demoqaLog.info(
				"✅ Test Widgets|Auto Complete - Select & Remove Flow|Type single color name - Remove Selection Completed");
	}

	@Test(priority = 11, description = "Test Widgets|Date Picker...")
	public void ClickDatePicker() {
		testRep = extentReportManager.createTest("Test Widgets|Date Picker...");
		testRep.info("🧪 Starting test for Widgets|Date Picker");
		demoqaLog.info("🧪 Starting Test Widgets|Date Picker...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String datePickerPage = widgetsPage.ClickDatePicker();
		
		Assert.assertEquals("Date Picker", datePickerPage);
		
		testRep.pass("✅ Assertion Confirmation Text: " + datePickerPage);
		testRep.pass("✅ Test Widgets|Date Picker Test Completed...");
		demoqaLog.info("✅ Widgets|Date Picker Test Completed...");
	}

	@Test(priority = 12, dataProvider = "DateSource", description = "Test Widgets|Date Picker|Select Date using input from Spreadsheet...")
	public void SelectDate(UserFormData data) {
		testRep = extentReportManager.createTest("Test Widgets|Date Picker|Select Date");
		testRep.info("🧪 Starting test for Widgets|Date Picker|Select Date");

		demoqaLog.info("🧪 Navigating to Widgets section...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();

		Optional<LocalDate> dateOpt = DataSanitizer.sanitizeDOBToDate(data.getDob(), "DOB", "SelectDateTest");
		if (dateOpt.isEmpty()) {
			demoqaLog.warn("⚠️ DOB parsing failed|Raw value: " + data.getDob());
			throw new RuntimeException("❌ Unable to parse DOB: " + data.getDob());
		}

		LocalDate dob = dateOpt.get();
		demoqaLog.info("📅 Parsed DOB: " + dob);
		Assert.assertNotNull(dob, "DOB should be parsable");

		widgetsPage.ClickDatePicker();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		// ✅ Use context-driven locator mapping
		DatePickerUtils.selectDate(driver, TestContext.DATE_PICKER, dob);

		testRep.pass("✅ Date selection completed successfully");
	}

	@Test(priority = 13, dataProvider = "DateSource", description = "Test Widgets|Date Picker|Date And Time using input from Spreadsheet...")
	public void SelectDateAndTime(UserFormData data) {
		testRep = extentReportManager.createTest("Test Widgets|Date Picker|Date And Time");
		testRep.info("🧪 Starting test for Widgets|Date Picker|Date And Time");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();

		// Parse DOB → LocalDateTime (default to midnight or any preferred time)
		Optional<LocalDate> dateOpt = DataSanitizer.sanitizeDOBToDate(data.getDob(), "DOB", "SelectDateTimeTest");
		Assert.assertTrue(dateOpt.isPresent(), "❌ DOB parsing failed|Raw value: " + data.getDob());

		LocalDate dob = dateOpt.get();
		LocalDateTime dobWithTime = dob.atTime(10, 30); // you can control time here
		demoqaLog.info("📅 Parsed DOB with time: " + dobWithTime);

		widgetsPage.ClickDatePicker();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		// ✅ Use DatePickerUtils for "Date And Time"
		DatePickerUtils.selectDateTime(driver, TestContext.DATE_TIME_PICKER, dobWithTime, demoqaLog);

		testRep.pass("✅ DateTime selection completed successfully");
	}

	@Test(priority = 14, description = "Test Widgets|Slider")
	public void ClickSlider() {
		testRep = extentReportManager.createTest("Test Widgets|Slider");
		testRep.info("🧪 Starting test for Widgets|Slider");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String sliderpageTitle = widgetsPage.ClickSlider();
		
		Assert.assertEquals("Slider", sliderpageTitle);
		testRep.pass("✅ Assertion Confirmation Text: " + sliderpageTitle);
		testRep.pass("✅ Test Widgets|Slider Test Completed...");
		demoqaLog.info("✅ Widgets|Slider Test Completed...");
	}

	@CaptureOnSuccess(description = "Slider Moved as per Spreadsheet Input - Successfully", screenshotMode = "viewport")
	@Test(priority = 15, dataProvider = "WebTableDataSourceAll") // Process All Rows in the Spreadsheet
//	@Test(priority = 15, dataProvider = "WebTableDataSourceSingle") //Process 1st Row in the Spreadsheet
	public void moveSliderBasedOnAge(WebTableUser user) {
		// 🔹 Reporting Setup
		testRep = extentRep.createTest("Test Widgets|Slider|Move Slider");
		testRep.info("🧪 Starting test for Widgets|Slider|Move Slider");

		// 🔹 Page Navigation
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickSlider();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		// 🔹 Slider Movement
		int expectedAge = user.getAge() / 10;
		int actualAge = widgetsPage.moveSliderToAge(user);

		testRep.info("📍 Slider moved to: " + actualAge);

		// 🔹 Validation
		try {
			Assert.assertEquals(actualAge, expectedAge, "Slider value mismatch");
			testRep.pass("✅ Slider moved correctly to target age.");
			testRep.pass("✅ Test Widgets|Slider|Move Slider Test Completed...");
			demoqaLog.info("✅ Slider moved correctly to target age.");
		} catch (AssertionError e) {
			testRep.fail("❌ Slider did not reach expected value. Expected: " + expectedAge + ", Actual: " + actualAge);
			demoqaLog.error("❌ Slider mismatch. Expected: " + expectedAge + ", Actual: " + actualAge);
			throw e;
		}
	}

	@Test(priority = 16, description = "Test Widgets|Progress Bar")
	public void ClickProgress() {
		testRep = extentReportManager.createTest("Test Widgets|Progress Bar");
		testRep.info("🧪 Starting test for Widgets|Progress Bar");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String progressBarPage = widgetsPage.ClickProgressBar();
		
		Assert.assertEquals("Progress Bar", progressBarPage);
		testRep.pass("✅ Assertion Confirmation Text: " + progressBarPage);
		testRep.pass("✅ Test Widgets|Progress Bar Test Completed...");
		demoqaLog.info("✅ Widgets|Progress Bar Test Completed...");
	}

	@CaptureOnSuccess(description = "Progress Bar Moved as per Spreadsheet Input - Successfully", screenshotMode = "viewport")
//	@Test(priority = 17, dataProvider = "WebTableDataSourceAll") //Process All Rows in the Spreadsheet
	@Test(priority = 17, dataProvider = "WebTableDataSourceSingle") // Process 1st Row in the Spreadsheet
	public void moveProgressBarBasedOnAge(WebTableUser user) {
		// 🔹 Reporting Setup
		testRep = extentRep.createTest("Test Widgets|Progress Bar|Move Progress Bar");
		testRep.info("🧪 Starting test for Widgets|Progress Bar|Move Progress Bar");

		// 🔹 Page Navigation
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickProgressBar();
		PageLoadHandler.waitUntilLoaded(driver, 30);

		// 🔹 Progress Bar Movement
		int expectedAge = user.getAge() / 10;
		int actualAge = widgetsPage.moveProgressBarToAge(user); // returns final value

		testRep.info("📍 Progress bar moved to: " + actualAge);

		// 🔹 Validation with tolerance
		int tolerance = 2; // ±2%
		try {
			Assert.assertTrue(Math.abs(actualAge - expectedAge) <= tolerance, "Progress bar value mismatch");
			testRep.pass(String.format("✅ Progress bar moved correctly. Expected ~%d%%, Actual: %d%%", expectedAge,
					actualAge));
			testRep.pass("✅ Test Widgets|Progress Bar|Move Progress Bar Test Completed...");
			demoqaLog.info("✅ Progress bar moved correctly. Expected ~{}%, Actual: {}%", expectedAge, actualAge);
		} catch (AssertionError e) {
			testRep.fail(String.format("❌ Progress bar did not reach expected range. Expected ~%d%%, Actual: %d%%",
					expectedAge, actualAge));
			demoqaLog.error("❌ Progress bar mismatch. Expected ~{}%, Actual: {}%", expectedAge, actualAge);
			throw e;
		}
	}
	
	@Test(priority = 18, description = "Test Widgets|Tabs")
	public void ClickTabs() {
		testRep = extentReportManager.createTest("Test Widgets|Tabs");
		testRep.info("🧪 Starting test for Widgets|Tabs");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		String tabsPageTitle = widgetsPage.ClickTabs();
		
		Assert.assertEquals("Tabs", tabsPageTitle);
		
		testRep.pass("✅ Assertion Confirmation Text: " + tabsPageTitle);
		testRep.pass("✅ Test Widgets|Tabs Test Completed...");
		demoqaLog.info("✅ Widgets|Tabs Test Completed...");
	}
	
	@Test(priority = 19, description = "Test Widgets|Tabs|What Tab")
	public void ClickWhatTab() {
		testRep = extentReportManager.createTest("Test Widgets|Tabs|What Tab");
		testRep.info("🧪 Starting test for Widgets|Tabs|What Tab");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickTabs();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String whatTabContent = widgetsPage.ClickWhatTab();
		
		Assert.assertEquals(true, whatTabContent.contains("Lorem Ipsum is simply dummy text of the printing"));
		
		testRep.pass("✅ Assertion Confirmation Text: " + whatTabContent);
		testRep.pass("✅ Test Widgets|Tabs|What Tab Test Completed...");
		demoqaLog.info("✅ Widgets|Tabs|What Tab Test Completed...");
	}
	
	@Test(priority = 20, description = "Test Widgets|Tabs|Origin Tab")
	public void ClickOriginTab() {
		testRep = extentReportManager.createTest("Test Widgets|Tabs|Origin Tab");
		testRep.info("🧪 Starting test for Widgets|Tabs|Origin Tab");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickTabs();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String originTabContent = widgetsPage.ClickOriginTab();
		
		Assert.assertEquals(true,
				originTabContent.contains("Contrary to popular belief, Lorem Ipsum is not simply random text"));
		
		testRep.pass("✅ Assertion Confirmation Text: " + originTabContent);
		testRep.pass("✅ Test Widgets|Tabs|Origin Tab Test Completed...");
		demoqaLog.info("✅ Widgets|Tabs|Origin Tab Test Completed...");
	}
	
	@Test(priority = 21, description = "Test Widgets|Tabs|Use Tab")
	public void ClickUseTab() {
		testRep = extentReportManager.createTest("Test Widgets|Tabs|Use Tab");
		testRep.info("🧪 Starting test for Widgets|Tabs|Use Tab");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickTabs();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		String useTabContent = widgetsPage.ClickUseTab();
		
		Assert.assertEquals(true, useTabContent.contains("It is a long established fact that a reader will be distracted by the readable content"));
		
		testRep.pass("✅ Assertion Confirmation Text: " + useTabContent);
		testRep.pass("✅ Test Widgets|Tabs|Use Tab Test Completed...");
		demoqaLog.info("✅ Widgets|Tabs|Use Tab Test Completed...");
	}
	
	@Test(priority = 22, description = "Test Widgets|Tool Tips")
	public void ClickToolTips() {
		testRep = extentReportManager.createTest("Test Widgets|Tool Tips");
		testRep.info("🧪 Starting test for Widgets|Tool Tips");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		String toolTipPg = widgetsPage.ClickToolTips();
		
		Assert.assertEquals(true, toolTipPg.contains("Practice Tool Tips"));
		
		testRep.pass("✅ Assertion Confirmation Text: " + toolTipPg);
		testRep.pass("✅ Test Widgets|Tool Tips Test Completed...");
		demoqaLog.info("✅ Widgets|Tool Tips Test Completed...");
	}
	
	@Test(priority = 22, description = "Test Widgets|Tool Tips")
	public void HoverMeToSeeBtn() {
		testRep = extentReportManager.createTest("Test Widgets|Tool Tips|Hover me to see - Button");
		testRep.info("🧪 Starting test for Widgets|Tool Tips|Hover me to see - Button");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickToolTips();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		
		String tooltipText = widgetsPage.HoverMeToSeeBtn();
		Assert.assertEquals(tooltipText, "You hovered over the Button");
		
		testRep.pass("✅ Assertion Confirmation Text: " + tooltipText);
		testRep.pass("✅ Test Widgets|Tool Tips|Hover me to see - Button Test Completed...");
		demoqaLog.info("✅ Widgets|Tool Tips|Hover me to see - Button Test Completed...");
	}
	
	@Test(priority = 22, description = "Test Widgets|Tool Tips|Hover me to see - Text Field")
	public void HoverMeToSeeTxtField() {
		testRep = extentReportManager.createTest("Test Widgets|Tool Tips|Hover me to see - Text Field");
		testRep.info("🧪 Starting test for Widgets|Tool Tips|Hover me to see - Text Field");

		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickToolTips();
		PageLoadHandler.waitUntilLoaded(driver, 30);
		
		String tooltipText = widgetsPage.HoverMeToSeeTxtField();
		Assert.assertEquals(tooltipText, "You hovered over the text field");
		
		testRep.pass("✅ Assertion Confirmation Text: " +tooltipText);
		testRep.pass("✅ Test Widgets|Tool Tips|Hover me to see - Text Field Test Completed...");
		demoqaLog.info("✅ Widgets|Tool Tips|Hover me to see - Text Field Test Completed...");
	}
}
