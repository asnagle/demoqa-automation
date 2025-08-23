package tests;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.demoqaBase;
import customAnnotations.CaptureOnSuccess;
import models.UserFormData;
import pages.formsPage;
import pages.homePage;
import utils.AssertFormData;
import utils.DataSanitizer;
import utils.ExcelUtils;
import utils.RetryUrlAccess;
//import utils.demoqaLog;
import utils.extentReportManager;

public class FormTests extends demoqaBase {
	@DataProvider(name = "UserFormData")
	public Object[][] provideUserFormData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		return ExcelUtils.getMappedData(filePath, "Sheet1", UserFormData.class);
	}
//	public Object[][] provideUserFormData() throws IOException {
//	    String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
//	    return new ExcelUtils().getMappedData(filePath, "Sheet1", UserFormData.class);
//	}

	@Test(priority = 1)
	public void FormsCard() {
		testRep = extentReportManager.createTest("Test Access to Form Card");
		testRep.info("Starting test for Form Card Access");
		demoqaLog.info("Starting Form Card Test...");
//		homePage homePage = new homePage(driver);
		formsPage formsPage = new formsPage(driver);
		formsPage.accessForms();
		System.out.println("Title of this Page is: " + driver.getTitle());
		testRep.info("Title of this Page is: " + driver.getTitle());
		testRep.info("Form Card Test Completed...");
		demoqaLog.info("Form Card Test Completed...");
	}

	@Test(priority = 2)
	public void FormButton() {

		testRep = extentReportManager.createTest("Test Access to Form Card|Practice Forms...");
		testRep.info("Starting test for Form Card Access|Practice Forms...");
		demoqaLog.info("Starting Form Card Test...");
		homePage homePage = new homePage(driver);
		homePage.clickFormCard();
		formsPage formsPage = new formsPage(driver);
		formsPage.clickPracticeForm();
		System.out.println("Title of this Page is: " + driver.getTitle());
		testRep.info("Title of this Page is: " + driver.getTitle());
		testRep.info("Form Card|Practice Forms Test Completed...");
		demoqaLog.info("Form Card|Practice Forms Test Completed...");
	}

//		================= Code for Data Driven Testing ===============
	@CaptureOnSuccess(description = "Form filled by taking data input from a Spreadsheet - Successfully", screenshotMode = "viewport")
	@Test(priority = 3, dataProvider = "UserFormData")
	public void fillForm(models.UserFormData data) {

//	public void testFormFilling(UserFormData data) {
		testRep = extentReportManager.createTest("Test Form Filling");
		testRep.info("Starting test for Form Filling");
		demoqaLog.info("Starting Form Filling Test...");

		formsPage formsPage = new formsPage(driver);
		testRep.info("Accessing Practice Form");
		RetryUrlAccess.navigateWithRetry(driver, "https://demoqa.com", 3);
		formsPage.accessForms();
		formsPage.clickPracticeForm();

		String fullName = data.getFirstName() + " " + data.getLastName();
		demoqaLog.info("Filling form for: " + fullName);

		// Basic Info
		formsPage.entFirstName(data.getFirstName());
		formsPage.entLastName(data.getLastName());
		formsPage.entEmail(data.getUserMail()); // optional utility
		formsPage.selectGender(data.getGender());
		formsPage.entMobileNo(data.getMobile());
//		DateComponents dob = data.getDob(); // Cast if needed
//		formsPage.selectDay(dob.getDay());
//		formsPage.selectMonth(dob.getMonth());
		LocalDate dob = LocalDate.parse(DataSanitizer.sanitizeDOB(data.getDob()),
				DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		System.out.println("📅 Parsed DOB: " + dob);

//		LocalDate dob = DataSanitizer.sanitizeDOB(dob);
		System.out.println("📅 Parsed DOB: " + dob);

		// Use dob in assertions or form submission logic
		assertNotNull(dob, "DOB should be parsable");

		formsPage.fillDob(data.getDob());
		formsPage.enterSubject(data.getSubject());
		formsPage.selectHobbies(data.getHobbies());
		formsPage.uploadPicture(data.getPicturePath());

		// Location
		formsPage.enterAddress(data.getAddress());
		formsPage.selectState(data.getState());
		formsPage.selectCity(data.getCity());

		// Submit
		formsPage.submitButton();
		// ✅ Wait for confirmation table to appear
		new WebDriverWait(driver, Duration.ofSeconds(5))
		    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table tbody tr")));

		// ✅ Assert confirmation popup matches POJO
		AssertFormData.assertConfirmation(data, driver, By.cssSelector("table tbody tr"), demoqaLog);

		testRep.pass("Test Form Filling Completed Successfully");
		demoqaLog.info("Test Form Filling Completed for: " + fullName);
	}

}
