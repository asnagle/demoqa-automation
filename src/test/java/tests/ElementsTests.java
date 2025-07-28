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
	
	@Test(priority=3)
	public void CheckBox() {
		testRep = extentReportManager.createTest("Test Check Box");
		testRep.info("Starting test for Elements | Check Box");
		demoqaLog.info("Starting Test Elements | Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		testRep.info("Test Elements | Check Box Test Completed...");
		demoqaLog.info("Test Elements | Check Box Test Test Completed...");
	}
	
	@Test(priority=4)
	public void SelectingHomeCheckBox() {
		testRep = extentReportManager.createTest("Test Selecting Home Check Box");
		testRep.info("Starting test for Elements | Selecting Home Check Box");
		demoqaLog.info("Starting Test Elements | Selecting Home Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.clickHomeCheckBox();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements | Selecting Home Test Completed...");
		demoqaLog.info("Elements | Selecting Home Test Completed...");
	}
	
	@Test(priority=5)
	public void SelectingDesktopCheckBox() {
		testRep = extentReportManager.createTest("Test Selecting Desktop Check Box");
		testRep.info("Starting test for Elements | Selecting Desktop Check Box");
		demoqaLog.info("Starting Test Elements | Selecting Desktop Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.DesktopCheckBox();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements | Home | Desktop Test Completed...");
		demoqaLog.info("Elements | Home | Desktop Test Completed...");
	}
	
	@Test(priority=6)
	public void SelectingNotesCommandsClick() {
		testRep = extentReportManager.createTest("Test Selecting Desktop| Notes & Commands Check Box");
		testRep.info("Starting test for Elements | Selecting Desktop| Notes & Commands Check Box");
		demoqaLog.info("Starting Test Elements | Selecting Desktop| Notes & Commands Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.selectNotesCommands();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements | Home | Desktop | Notes & Commands Test Completed...");
		demoqaLog.info("Elements | Home | Desktop | Notes & Commands Completed...");
	}
	
	@Test(priority=7)
	public void SelectingDocumentsCheckBox() {
		testRep = extentReportManager.createTest("Test Selecting Documents Check Box");
		testRep.info("Starting test for Elements | Selecting Documents Check Box");
		demoqaLog.info("Starting Test Elements | Selecting Documents Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.DocumentsCheckbox();
		elementsPage.validateSelectedCheckBox();
	}
	
	@Test(priority=8)
	public void SelectingWorkSpace() {
		testRep = extentReportManager.createTest("Test Selecting Documents| WorkSpace Check Box");
		testRep.info("Starting test for Elements | Home | Selecting Documents | WorkSpace Check Box");
		demoqaLog.info("Starting Test Elements | Home | Selecting Documents | WorkSpace Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.selectWorkSpace();
		elementsPage.validateSelectedCheckBox();
	}
	
	@Test(priority=9)
	public void SelectReact() {
		testRep = extentReportManager.createTest("Test Selecting Documents|WorkSpace|React Check Box");
		testRep.info("Starting test for Elements|Home|Documents| Selecting WorkSpace|React Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|WorkSpace|React Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleWorkSpace();
		elementsPage.selectReact();
		elementsPage.validateSelectedCheckBox();
	}
	
	@Test(priority=10)
	public void SelectAngular() {
		testRep = extentReportManager.createTest("Test Selecting Documents|WorkSpace|Angular Check Box");
		testRep.info("Starting test for Elements|Home|Documents| Selecting WorkSpace|Angular Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|WorkSpace|Angular Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleWorkSpace();
		elementsPage.selectAngular();
		elementsPage.validateSelectedCheckBox();
	}
	
	@Test(priority=11)
	public void SelectVeu() {
		testRep = extentReportManager.createTest("Test Selecting Documents|WorkSpace|Angular Check Box");
		testRep.info("Starting test for Elements|Home|Documents| Selecting WorkSpace|Angular Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|WorkSpace|Angular Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleWorkSpace();
		elementsPage.selectVeu();
		elementsPage.validateSelectedCheckBox();
	}
	
	@Test(priority=12)
	public void SelectReactAnuglarVeu() {
		testRep = extentReportManager.createTest("Test Selecting Documents|WorkSpace|Angular Check Box");
		testRep.info("Starting test for Elements|Home|Documents| Selecting WorkSpace|Angular Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|WorkSpace|Angular Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleWorkSpace();
		elementsPage.selectReact();
		elementsPage.selectAngular();
		elementsPage.selectVeu();
		elementsPage.validateSelectedCheckBox();
	}
	@Test(priority=13)
	public void SelectingOffice() {
		testRep = extentReportManager.createTest("Test Selecting Documents| Office Check Box");
		testRep.info("Starting test for Elements | Home | Selecting Documents | Office Check Box");
		demoqaLog.info("Starting Test Elements | Home | Selecting Documents | Office Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.selectOffice();
		elementsPage.validateSelectedCheckBox();
	}
}
