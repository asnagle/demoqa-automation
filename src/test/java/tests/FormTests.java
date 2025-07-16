package tests;

import org.testng.annotations.Test;
import base.demoqaBase;
import customAnnotations.CaptureOnSuccess;
import pages.formsPage;
import pages.homePage;
import utils.demoqaLog;
import utils.extentReportManager;

public class FormTests extends demoqaBase{
	@Test(priority = 1)
	public void FormCard() {
		
		testRep = extentReportManager.createTest("Test Access to Form Card");
		testRep.info("Starting test for Form Card Access");
		demoqaLog.info("Starting Form Card Test...");
		homePage homePage = new homePage(driver);
		homePage.clickFormCard();
		homePage.clickPracticeForm();
		System.out.println("Title of this Page is: " + driver.getTitle());
		testRep.info("Title of this Page is: " + driver.getTitle());
		testRep.info("Form Card Test Completed...");
		demoqaLog.info("Form Card Test Completed...");
	}

	@CaptureOnSuccess(description = "Checks Form can be filled Successfully", screenshotMode = "viewport")
	@Test(priority = 2)
	public void fillForm() {
		
		testRep = extentReportManager.createTest("Test Form Filling");
		testRep.info("Starting test for Form Filling");
		demoqaLog.info("Starting Form Filling Test...");
		formsPage formsPage = new formsPage(driver);
		testRep.info("Accessing Practice Form");
		formsPage.accessForms();
		testRep.info("Entering Data in the Form");
		formsPage.entFirstName();
		formsPage.entLastName();
		testRep.info("Entered FirstName & LastName");
		formsPage.entEmail();
		formsPage.selectGender();
		formsPage.entmobileNo();
		formsPage.entDob();
		testRep.info("Entered Email, Gender, Mobile No & Date of Birth ");
		formsPage.enterSubject();
		formsPage.selectHobbies();
		formsPage.uploadPicture();
		testRep.info("Selected Subject, Hobbies & Uploaded Picture");
		formsPage.enterAddress();
		formsPage.selectState();
		formsPage.selectCity();
		testRep.info("Entered Address, Selected State & City");
		formsPage.submitButton();
		demoqaLog.info("Completed Form Filling Test...");
		testRep.pass("Completed Form Filling Testing");
	}
	

}
