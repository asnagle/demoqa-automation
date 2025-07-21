package tests;

import org.testng.annotations.Test;

import base.demoqaBase;
import customAnnotations.CaptureOnSuccess;
import pages.elementsPage;
import utils.demoqaLog;
import utils.extentReportManager;

public class ElementsTests extends demoqaBase {
	
	@Test(priority=1)
	public void ElementsCard() {
		testRep = extentReportManager.createTest("Test Elements");
		testRep.info("Starting test for Elements | Text Box");
		demoqaLog.info("Starting Test Elements | Text Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		System.out.println("Title of this Page is: " + driver.getTitle());
		testRep.info("Title of this Page is: " + driver.getTitle());
		testRep.info("Element Card Test Completed...");
		demoqaLog.info("Element Card Test Completed...");
		
	}
	
	@CaptureOnSuccess(description = "Text Box Filled by Static data Input - Successfully", screenshotMode = "viewport")
	@Test(priority=2)
	public void TextBox() {
		testRep = extentReportManager.createTest("Test Text Box");
		testRep.info("Starting test for Elements | Text Box");
		demoqaLog.info("Starting Test Elements | Text Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickTextBox();
		testRep.info("Title of this Page is: " + driver.getTitle());
		demoqaLog.info("Title of this Page is: " + driver.getTitle());
		elementsPage.enterFullName();
		elementsPage.enterEmail();
		elementsPage.enterCurrentAddress();
		elementsPage.enterPermanentAddress();
		elementsPage.submitButton();
		elementsPage.validateTextBox();
		testRep.info("Submitted Data is captured in screenshot attached...");
		testRep.info("Text Box Element Test Completed...");
		demoqaLog.info("Text Box Element Test Completed...");
		
	}


}
