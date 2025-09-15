package tests;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.demoqaBase;
import models.ColorSelectionData;
import models.UserFormData;
import pages.WidgetsPage;
import utils.DataSanitizer;
import utils.DatePickerUtils;
import utils.ExcelUtils;
import utils.PageLoadHandler;
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

	@Test(priority = 1)
	public void accessWidgets() {
		testRep = extentReportManager.createTest("Test Widgets...");
		testRep.info("🧪 Starting test for Widgets");
		demoqaLog.info("🧪 Starting Test Widgets...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		testRep.info("✅ Test Alerts, Frames & Windows Test Completed...");
		demoqaLog.info("✅ Widgets Test Completed...");
	}

	@Test(priority = 2)
	public void Accordian() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian...");
		testRep.info("🧪 Starting test for Widgets|Accordian");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		PageLoadHandler.waitUntilLoaded(driver, 15);

		widgetsPage.ClickAccordian();
		testRep.info("✅ Test Widgets|Accordian Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian Test Completed...");
	}

	@Test(priority = 3)
	public void AccessWhatis() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian|What is Lorem Ipsum?...");
		testRep.info("🧪 Starting test for Widgets|Accordian|What is Lorem Ipsum?");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian|What is Lorem Ipsum?...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAccordian();
		PageLoadHandler.waitUntilLoaded(driver, 15);

		widgetsPage.AccessWhatis();
		testRep.info("✅ Test Widgets|Accordian|What is Lorem Ipsum? Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian|What is Lorem Ipsum? Test Completed...");
	}

	@Test(priority = 4)
	public void AccessWhereDoes() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian|Where does it come from?...");
		testRep.info("🧪 Starting test for Widgets|Accordian|Where does it come from?");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian|Where does it come from?...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAccordian();
		PageLoadHandler.waitUntilLoaded(driver, 15);

		widgetsPage.AccessWhereDoesIt();
		testRep.info("✅ Test Widgets|Accordian|Where does it come from? Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian|Where does it come from? Test Completed...");
	}

	@Test(priority = 5)
	public void AccessWhyDoWe() {
		testRep = extentReportManager.createTest("Test Widgets|Accordian|Why do we use it?...");
		testRep.info("🧪 Starting test for Widgets|Accordian|Why do we use it?");
		demoqaLog.info("🧪 Starting Test Widgets|Accordian|Why do we use it?...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAccordian();
		PageLoadHandler.waitUntilLoaded(driver, 15);

		widgetsPage.AccessWhyDoWe();
		testRep.info("✅ Test Widgets|Accordian|Why do we use it? Test Completed...");
		demoqaLog.info("✅ Widgets|Accordian|Why do we use it? Test Completed...");
	}

	@Test(priority = 6)
	public void AutoComplete() {
		testRep = extentReportManager.createTest("Test Widgets|Auto Complete...");
		testRep.info("🧪 Starting test for Widgets|Auto Complete");
		demoqaLog.info("🧪 Starting Test Widgets|Auto Complete...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickAutoComplete();
		testRep.pass("✅ Test Widgets|Auto Complete Test Completed...");
		demoqaLog.info("✅ Widgets|Auto Complete Test Completed...");
	}

	@Test(priority = 7, dataProvider = "ColorSelectionData")
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

	@Test(priority = 8, dataProvider = "ColorSelectionDataRemove")
	public void AutoCompleteSearchRemove(ColorSelectionData removalData) throws IOException {
		testRep = extentReportManager.createTest("Test Widgets | Auto Complete - Select & Remove Flow");
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

	@Test(priority = 9, dataProvider = "SingleColorData")
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

	@Test(priority = 10, dataProvider = "SingleColorData")
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

	@Test(priority = 11)
	public void ClickDatePicker() {
		testRep = extentReportManager.createTest("Test Widgets|Date Picker...");
		testRep.info("🧪 Starting test for Widgets|Date Picker");
		demoqaLog.info("🧪 Starting Test Widgets|Date Picker...");
		WidgetsPage widgetsPage = new WidgetsPage(driver);
		widgetsPage.accessWidgets();
		widgetsPage.ClickDatePicker();
		testRep.pass("✅ Test Widgets|Date Picker Test Completed...");
		demoqaLog.info("✅ Widgets|Date Picker Test Completed...");
	}

	@Test(priority = 12, dataProvider = "DateSource")
	public void SelectDate(UserFormData data) {
	    testRep = extentReportManager.createTest("Test Widgets | Date Picker | Select Date");
	    testRep.info("🧪 Starting test for Widgets | Date Picker");

	    demoqaLog.info("🧪 Navigating to Widgets section...");
	    WidgetsPage widgetsPage = new WidgetsPage(driver);
	    widgetsPage.accessWidgets();

	    Optional<LocalDate> dateOpt = DataSanitizer.sanitizeDOBToDate(data.getDob(), "DOB", "SelectDateTest");
	    if (!dateOpt.isPresent()) {
	        demoqaLog.warn("⚠️ DOB parsing failed | Raw value: " + data.getDob());
	        throw new RuntimeException("❌ Unable to parse DOB: " + data.getDob());
	    }

	    LocalDate dob = dateOpt.get();
	    demoqaLog.info("📅 Parsed DOB: " + dob);
	    Assert.assertNotNull(dob, "DOB should be parsable");
	    widgetsPage.ClickDatePicker();
	    PageLoadHandler.waitUntilLoaded(driver, 30);

	    // ✅ Use correct locator for Widgets > Date Picker
//	    DatePickerUtils.selectDate(driver, By.id("datePickerMonthYearInput"), dob);
	    DatePickerUtils.selectDate(driver, By.xpath("//input[@id='datePickerMonthYearInput']"), dob);

	    testRep.pass("✅ Date selection completed successfully");
	}

}
