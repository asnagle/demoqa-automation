package tests;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.demoqaBase;
import customAnnotations.CaptureOnSuccess;
import enums.TestContext;
import models.UserFormData;
import pages.formsPage;
import pages.homePage;
import utils.AssertFormData;
import utils.ConfirmationTableParser;
import utils.DataSanitizer;
import utils.DatePickerUtils;
import utils.ExcelUtils;
import utils.PageLoadHandler;
import utils.PojoSanitizer;
import utils.RetryUrlAccess;
//import utils.demoqaLog;
import utils.extentReportManager;

public class FormTests extends demoqaBase {
	@DataProvider(name = "UserFormData")
	public Object[][] provideUserFormData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		return ExcelUtils.getMappedData(filePath, "Sheet1", UserFormData.class);
	}

	@Test(priority = 1, description = "Test Access to Form Card")
	public void FormsCard() {
		testRep = extentReportManager.createTest("Test Access to Form Card");
		testRep.info("🧪 Starting test for Form Card Access");
		demoqaLog.info("🧪 Starting Form Card Test...");
		formsPage formsPage = new formsPage(driver);
		formsPage.accessForms();
		System.out.println("Title of this Page is: " + driver.getTitle());
		testRep.info("Title of this Page is: " + driver.getTitle());
		testRep.pass("✅ Test Form Card Test Completed...");
		demoqaLog.info("✅ Form Card Test Completed...");
	}

	@Test(priority = 2, description = "Test Access to Form Card|Practice Forms...")
	public void FormButton() {

		testRep = extentReportManager.createTest("Test Access to Form Card|Practice Forms...");
		testRep.info("🧪 Starting test for Form Card Access|Practice Forms...");
		demoqaLog.info("🧪 Starting Form Card Test...");
		homePage homePage = new homePage(driver);
		homePage.clickFormCard();
		formsPage formsPage = new formsPage(driver);
		formsPage.clickPracticeForm();
		System.out.println("Title of this Page is: " + driver.getTitle());
		testRep.info("Title of this Page is: " + driver.getTitle());
		testRep.pass("✅ Test Form Card|Practice Forms Test Completed...");
		demoqaLog.info("✅ Form Card|Practice Forms Test Completed...");
	}

//		================= Code for Data Driven Testing ===============
	@Test(priority = 3, dataProvider = "UserFormData")
	@CaptureOnSuccess(description = "Form filled by taking data input from a Spreadsheet - Successfully", screenshotMode = "viewport")
	public void fillForm(models.UserFormData data) {

	    testRep = extentReportManager.createTest("Test Form Filling");
	    testRep.info("🧪 Starting test for Form Filling");
	    demoqaLog.info("🧪 Starting Form Filling Test...");

	    formsPage formsPage = new formsPage(driver);
	    testRep.info("Accessing Practice Form");
	    RetryUrlAccess.navigateWithRetry(driver, "https://demoqa.com", 3);
	    formsPage.accessForms();
	    formsPage.clickPracticeForm();
	    PageLoadHandler.waitUntilLoaded(driver, 30);

	    String fullName = data.getFirstName() + " " + data.getLastName();
	    demoqaLog.info("Filling form for: " + fullName);

	    // ✅ Basic Info
	    formsPage.entFirstName(data.getFirstName());
	    formsPage.entLastName(data.getLastName());
	    formsPage.entEmail(data.getUserMail());
	    formsPage.selectGender(data.getGender());
	    formsPage.entMobileNo(data.getMobile());

	    // ✅ DOB Handling with Optional<LocalDate>
	    Optional<LocalDate> dobOpt = DataSanitizer.sanitizeDOBToDate(data.getDob(), "DOB", fullName);
	    if (dobOpt.isEmpty()) {
	        demoqaLog.warn("⚠️ DOB parsing failed for: " + fullName + " | Raw: " + data.getDob());
	        throw new RuntimeException("DOB could not be parsed for user: " + fullName);
	    }

	    LocalDate dob = dobOpt.get();
	    demoqaLog.info("📅 Parsed DOB for " + fullName + ": " + dob);
	    assertNotNull(dob, "DOB should be parsable");

	    // ✅ Use context-driven locator for Forms page
	    DatePickerUtils.selectDate(driver, TestContext.FORMS, dob);

	    // ✅ Academic & Preferences
	    formsPage.enterSubject(data.getSubject());
	    formsPage.selectHobbies(data.getHobbies());
	    formsPage.uploadPicture(data.getPicturePath());

	    // ✅ Location
	    formsPage.enterAddress(data.getAddress());
	    formsPage.selectState(data.getState());
	    formsPage.selectCity(data.getCity());

	    // ✅ Submit & Validate
	    formsPage.submitButton();

	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table tbody tr")));

	    Map<String, String> expectedValues = PojoSanitizer.extractSanitizedFields(data);
	    Map<String, String> actualValues = ConfirmationTableParser.extractValues(driver, By.cssSelector("table tbody tr"));

	    AssertFormData.assertConfirmation(expectedValues, actualValues, TestContext.FORMS);

	    testRep.pass("✅ Test Form Filling Completed Successfully");
	    demoqaLog.info("✅ Form Filling Completed for: " + fullName);
	}
}
