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
		testRep.info("Test Elements | Selecting Documents Check Box Test Completed...");
		demoqaLog.info("Test Elements | Selecting Documents Check Box Test Completed...");
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
		testRep.info("Test Elements | Home | Selecting Documents | WorkSpace Check Box Test Completed...");
		demoqaLog.info("Test Elements | Home | Selecting Documents | WorkSpace Check Box Completed...");
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
		testRep.info("Test Elements|Home|Documents| Selecting WorkSpace|React Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents| Selecting WorkSpace|React Check Box Completed...");
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
		testRep.info("Test Elements|Home|Documents| Selecting WorkSpace|Angular Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents| Selecting WorkSpace|Angular Check Box Completed...");
	}
	
	@Test(priority=11)
	public void SelectVeu() {
		testRep = extentReportManager.createTest("Test Selecting Documents|WorkSpace|Veu Check Box");
		testRep.info("Starting test for Elements|Home|Documents| Selecting WorkSpace|Veu Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|WorkSpace|Veu Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleWorkSpace();
		elementsPage.selectVeu();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Documents| Selecting WorkSpace|Veu Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents| Selecting WorkSpace|Veu Check Box Completed...");
	}
	
	@Test(priority=12)
	public void SelectReactAnuglarVeu() {
		testRep = extentReportManager.createTest("Test Selecting Documents|WorkSpace|React-Anuglar-Veu Check Box");
		testRep.info("Starting test for Elements|Home|Documents| Selecting WorkSpace|React-Anuglar-Veu Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|WorkSpace|React-Anuglar-Veu Check Box Test...");
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
		testRep.info("Test Elements|Home|Documents| Selecting WorkSpace|React-Anuglar-Veu Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents| Selecting WorkSpace|React-Anuglar-Veu Check Box Completed...");
	}
	
	@Test(priority=13)
	public void SelectingOffice() {
		testRep = extentReportManager.createTest("Test Selecting Documents|Office Check Box");
		testRep.info("Starting test for Elements|Home|Selecting Documents|Office Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|Office Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.selectOffice();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Selecting Documents|Office Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Selecting Documents|Office Check Box Completed...");
	}
	
	@Test(priority=14)
	public void SelectPublic() {
		testRep = extentReportManager.createTest("Test Documents|Office|Selecting Public Check Box");
		testRep.info("Starting test for Elements|Home|Documents|Office|Selecting Public Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|Office|Public Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleOffice();
		elementsPage.selectPublic();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Documents|Office|Selecting Public Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents|Office|Selecting Public Check Box Completed...");
	}
	
	@Test(priority=15)
	public void SelectPrivate() {
		testRep = extentReportManager.createTest("Test Selecting Documents|Office|Selecting Private Check Box");
		testRep.info("Starting test for Elements|Home|Documents|Office|Selecting Private Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|Office|Private Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleOffice();
		elementsPage.selectPrivate();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Documents|Office|Selecting Private Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents|Office|Selecting Private Check Box Completed...");
	}
	
	@Test(priority=16)
	public void SelectClassified() {
		testRep = extentReportManager.createTest("Test Selecting Documents|Office|Selecting Classified Check Box");
		testRep.info("Starting test for Elements|Home|Documents|Office|Selecting Classified Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|Office|Classified Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleOffice();
		elementsPage.selectClassified();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Documents|Office|Selecting Classified Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents|Office|Selecting Classified Check Box Completed...");
	}
	
	@Test(priority=17)
	public void SelectGeneral() {
		testRep = extentReportManager.createTest("Test Selecting Documents|Office|Selecting General Check Box");
		testRep.info("Starting test for Elements|Home|Documents|Office|Selecting General Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|Office|General Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleOffice();
		elementsPage.selectGeneral();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Documents|Office|Selecting General Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents|Office|Selecting General Check Box Completed...");
	}
	
	@Test(priority=18)
	public void SelectOfficeAll() {
		testRep = extentReportManager.createTest("Test Selecting Documents|Office|Selecting All Check Boxes");
		testRep.info("Starting test for Elements|Home|Documents|Office|Selecting All Check Boxes");
		demoqaLog.info("Starting Test Elements|Home|Selecting Documents|Office|All Check Boxes Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDocuments();
		elementsPage.ToggleOffice();
		elementsPage.selectPublic();
		elementsPage.selectPrivate();
		elementsPage.selectClassified();
		elementsPage.selectGeneral();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Documents|Office|Selecting All Check Boxes Test Completed...");
		demoqaLog.info("Test Elements|Home|Documents|Office|Selecting All Check Boxes Completed...");
	}
	
	@Test(priority=19)
	public void SelectingDownloads() {
		testRep = extentReportManager.createTest("Test Selecting Downloads Check Box");
		testRep.info("Starting test for Elements|Home|Selecting Downloads Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Downloads Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.selectDownloads();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Selecting Downloads Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Selecting Downloads Check Box Completed...");
	}
	
	@Test(priority=20)
	public void SelectingWordFile() {
		testRep = extentReportManager.createTest("Test Selecting Downloads|Word File Check Box");
		testRep.info("Starting test for Elements|Home|Selecting Downloads|Word File Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Downloads|Word File Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDownloads();
		elementsPage.selectWordFile();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Selecting Downloads|Word File Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Selecting Downloads|Word File Check Box Completed...");
	}
	
	@Test(priority=21)
	public void SelectingExcelFile() {
		testRep = extentReportManager.createTest("Test Selecting Downloads|Excel File Check Box");
		testRep.info("Starting test for Elements|Home|Selecting Downloads|Excel File Check Box");
		demoqaLog.info("Starting Test Elements|Home|Selecting Downloads|Excel File Check Box Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDownloads();
		elementsPage.selectExcelFile();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Selecting Downloads|Excel File Check Box Test Completed...");
		demoqaLog.info("Test Elements|Home|Selecting Downloads|Excel File Check Box Completed...");
	}
	
	@Test(priority=22)
	public void SelectingAllFiles() {
		testRep = extentReportManager.createTest("Test Selecting Downloads|Word & Excel File Check Boxes");
		testRep.info("Starting test for Elements|Home|Selecting Downloads|Word & Excel File Check Boxes");
		demoqaLog.info("Starting Test Elements|Home|Selecting Downloads|Word & Excel File Check Boxes Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.clickCheckBox();
		elementsPage.ToggleHome();
		elementsPage.ToggleDownloads();
		elementsPage.selectWordFile();
		elementsPage.selectExcelFile();
		elementsPage.validateSelectedCheckBox();
		testRep.info("Test Elements|Home|Selecting Downloads|Word & Excel File Check Boxes Test Completed...");
		demoqaLog.info("Test Elements|Home|Selecting Downloads|Word & Excel File Check Boxes Completed...");
	}
}
