package tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.demoqaBase;
import customAnnotations.CaptureOnSuccess;
import models.WebTableUser;
import pages.elementsPage;
import utils.ExcelUtils;
import utils.WebTableManager;
import utils.demoqaLog;
import utils.extentReportManager;
import utils.waitForElement;

public class ElementsTests extends demoqaBase {

	@DataProvider(name = "WebTableUserData")
	public Object[][] provideWebTableUserData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		String sheetName = "Sheet1";

		return ExcelUtils.getMappedData(filePath, sheetName, WebTableUser.class);
	}

	@DataProvider(name = "WebTableUserData2")
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

//	public Object[][] provideWebTableUserData() throws IOException {
//	    String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
//	    String sheetName = "Sheet1";
//
//	    ExcelUtils excelUtils = new ExcelUtils();
//	    return excelUtils.getMappedData(filePath, sheetName, WebTableUser.class);
//	}

	@DataProvider(name = "WTFormDataEdit")
	public Object[][] getSingleUserData() {
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		String sheetName = "Sheet1";
		WebTableUser user = ExcelUtils.getFirstUserFromExcel(filePath, sheetName);
		return new Object[][] { { user } };
	}

	@Test(priority = 1)
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
	@Test(priority = 2)
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

	@Test(priority = 3)
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

	@Test(priority = 4)
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

	@Test(priority = 5)
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

	@Test(priority = 6)
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

	@Test(priority = 7)
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

	@Test(priority = 8)
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

	@Test(priority = 9)
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

	@Test(priority = 10)
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

	@Test(priority = 11)
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

	@Test(priority = 12)
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

	@Test(priority = 13)
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

	@Test(priority = 14)
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

	@Test(priority = 15)
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

	@Test(priority = 16)
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

	@Test(priority = 17)
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

	@Test(priority = 18)
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

	@Test(priority = 19)
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

	@Test(priority = 20)
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

	@Test(priority = 21)
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

	@Test(priority = 22)
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

	@Test(priority = 23)
	public void clickRadioButton() {
		testRep = extentReportManager.createTest("Accessing Radio Button Page...");
		testRep.info("Starting test for Accessing Radio Button Page");
		demoqaLog.info("Starting Test Elements|Radio Button Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.radioButtonClick();
		elementsPage.validateRadioButtonClick();
		testRep.info("Test Elements|Radio Button Completed...");
		demoqaLog.info("Test Elements|Radio Button Completed...");
	}

	@Test(priority = 24)
	public void SelectYesRadioButton() {
		testRep = extentReportManager.createTest("Test Yes Radio Button Selection...");
		testRep.info("Starting test for Selectng Yes Radio Button");
		demoqaLog.info("Starting Test Elements|Yes Radio Button Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.radioButtonClick();
		elementsPage.yesRadioSelect();
		elementsPage.validateRadioSelection();
		testRep.info("Test Elements|Yes Radio Button Completed...");
		demoqaLog.info("Test Elements|Yes Radio Button Completed...");
	}

	@Test(priority = 25, dependsOnMethods = { "clickRadioButton" })
	public void SelectImpressiveRadioButton() {
		testRep = extentReportManager.createTest("Test Impressive Radio Button Selection...");
		testRep.info("Starting test for Selectng Impressive Radio Button");
		demoqaLog.info("Starting Test Elements|Yes Radio Button Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.radioButtonClick();
		elementsPage.impressiveRadioSelect();
		elementsPage.validateRadioSelection();
		testRep.info("Test Elements|Radio Button|Impressive Radio Button Completed...");
		demoqaLog.info("Test Elements|Radio Button|Impressive Completed...");
	}

	@Test(priority = 26)
	public void WebTablesClick() {
		testRep = extentReportManager.createTest("Test Elements|Web Tables ...");
		testRep.info("Starting test for Web Tables...");
		demoqaLog.info("Starting Test Elements|Web Tables Test...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.webTablesClick();
		testRep.info("Test Elements|Web Tables Completed...");
		demoqaLog.info("Test Elements|Web Tables Completed...");
	}

	@Test(priority = 27, dataProvider = "WebTableUserData", dependsOnMethods = { "WebTablesClick" })
	public void webTableNewRegistration(WebTableUser user) {
		testRep = extentReportManager.createTest("Test Elements|Web Tables|Registration Form Create User...");
		testRep.info("Starting test for Web Tables|Registration Form Create User...");
		demoqaLog.info("Starting Test Elements|Web Tables|Registration Form Create User...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.webTablesClick();
		elementsPage.webTablesNewRegistration();
		// ✅ Use the user directly — no need to re-fetch from Excel
		elementsPage.fillWebTableForm(user);
		elementsPage.assertUserPresentInTable(user, testRep);
		testRep.info("Test Elements|Web Tables|Registration Form Create User Completed...");
		demoqaLog.info("Test Elements|Web Tables|Registration Form Create User Completed...");
	}

	@Test(priority = 28, dataProvider = "WTFormDataEdit", dependsOnMethods = { "WebTablesClick" })
	public void webTableAddSearch(WebTableUser user) {
		testRep = extentReportManager.createTest("Test Registration Form Add & Search...");
		testRep.info("Starting test for Registration Form Add & Search...");
		demoqaLog.info("Starting Test Registration Form Add & Search...");

		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.webTablesClick();
		elementsPage.webTablesNewRegistration();

		// ✅ Use the user directly — no need to re-fetch from Excel
		elementsPage.fillWebTableForm(user);

		elementsPage.wTSearchBox(user.getFirstName());
		elementsPage.assertUserPresentInTable(user, testRep);

		testRep.info("Test for Registration Form Add & Search Completed...");
		demoqaLog.info("Test for Registration Form Add & Search Completed...");
	}

	@Test(priority = 29, dataProvider = "WTFormDataEdit", dependsOnMethods = { "WebTablesClick" })
	public void webTableAddEditFirstName(WebTableUser user) {
		testRep = extentReportManager.createTest("Test Registration Form Modify First Name...");
		testRep.info("Starting Test for Registration Form Modify First Name...");
		demoqaLog.info("Starting Test Registration Form Modify First Name...");

		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.webTablesClick();
		elementsPage.webTablesNewRegistration();

		// ✅ Use the user directly — no need to re-fetch from Excel
		elementsPage.fillWebTableForm(user);
//		WebTableUser user = WebTableUser.fromExcelRow(); // Your POJO
		String wTFirstname = user.getFirstName(); // Direct getter
		testRep.info("Orignal First Name of the User is: " + wTFirstname);

		elementsPage.editUserByField(wTFirstname);
		elementsPage.editFirstName(wTFirstname);
		user.setFirstName("Tonny");

//		elementsPage.wTSearchBox(user.getFirstName());
		elementsPage.assertUserPresentInTable(user, testRep);
		testRep.info("Updated First Name of the User is: " + user.getFirstName());

		testRep.pass("Test Registration Form Modify First Name Completed...");
		demoqaLog.info("Test Registration Form Modify First Name Completed Successfully...");
	}

	@Test(priority = 30, dataProvider = "WTFormDataEdit", dependsOnMethods = { "WebTablesClick" })
	public void webTableAddSearchEdit(WebTableUser user) {
		testRep = extentReportManager.createTest("Test Registration Form Search & Modify First Name...");
		testRep.info("Starting Test for Registration Form Search & Modify First Name...");
		demoqaLog.info("Starting Test Registration Form Search & Modify First Name...");

		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.webTablesClick();
		elementsPage.webTablesNewRegistration();

		// ✅ Use the user directly — no need to re-fetch from Excel
		elementsPage.fillWebTableForm(user);
		String wTFirstname = user.getFirstName(); // Direct getter
		testRep.info("Orignal First Name of the User is: " + wTFirstname);
		elementsPage.SearcheditUserByField(wTFirstname);

		String updatedName = "Tonny";
		elementsPage.SearcheditFirstName(updatedName);
		user.setFirstName("Tonny");
		elementsPage.assertUserPresentInTable(user, testRep);
		testRep.info("Updated First Name of the User is: " + user.getFirstName());
		testRep.pass("Test Registration Form Search & Modify First Name Completed...");
		demoqaLog.info("Test Registration Form Search & Modify First Name Completed Successfully...");
	}

	@Test(priority = 31, dataProvider = "WTFormDataEdit", dependsOnMethods = { "WebTablesClick" })
	public void webTableDeleteUser(WebTableUser user) {
		testRep = extentReportManager.createTest("Test Deleting User from Web Table...");
		testRep.info("Starting Test for Deleting User from Web Table...");
		demoqaLog.info("Starting Test for Deleting User from Web Table...");
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		elementsPage.webTablesClick();
		elementsPage.webTablesNewRegistration();

		// ✅ Use the user directly — no need to re-fetch from Excel
		elementsPage.fillWebTableForm(user);
		String wTFirstname = user.getFirstName(); // Direct getter
		testRep.info("Deleting User from Webtable: " + user.getFirstName());
		demoqaLog.info("Deleting User from Webtable: " + user.getFirstName());
		elementsPage.DeleteUser(wTFirstname);
		elementsPage.assertUserNotPresentInTable(user, testRep);
		testRep.pass("Test for Deleting User from Web Table Completed...");
		demoqaLog.info("Test for Deleting User from Web Table Completed Successfully...");
	}

	@CaptureOnSuccess(description = "Web Table filled by taking data input from a Spreadsheet - Successfully", screenshotMode = "viewport")
	@Test(priority = 32, dependsOnMethods = { "WebTablesClick" }) // Do not user data provider
	public void testAddAllUsersFromExcel() throws IOException {
		testRep = extentReportManager.createTest("Test Bulk User Creation in Web Table...");
		testRep.info("Starting Test for Bulk User Creation in Web Table...");
		// Initialize page and table manager
		elementsPage elementsPage = new elementsPage(driver);
		WebTableManager tableManager = new WebTableManager(driver);
		elementsPage.accessElements();
		elementsPage.webTablesClick();
		// Step 1: Capture initial user count
		int initialUserCount = tableManager.getUserCount();

		// Step 2: Prepare Excel data
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		String sheetName = "Sheet1";

		Object[][] data = ExcelUtils.getUsersFromExcel(filePath, sheetName);
		System.out.println("Declared Object: " + data);

		elementsPage.webTablesNewRegistration();
		elementsPage.createAllUsersFromExcel(filePath, sheetName); // This adds all users
		waitForElement.isElementVisible(driver, By.id("submit"));
		elementsPage.closeFormManually();

		// Step 4: Validate final user count
		int finalUserCount = tableManager.getUserCount();

		System.out.println("Initial user count: " + initialUserCount);
		System.out.println("Final user count: " + finalUserCount);

//		Assert.assertEquals(finalUserCount, expectedTotalCount, "Mismatch in user count after form submission");
		testRep.pass("Finished Test for Bulk User Creation in Web Table...");
		demoqaLog.info("Test for Bulk User Creation in Web Table Completed Successfully...");
	}

	@Test(priority = 33, dependsOnMethods = { "WebTablesClick" })
	public void testAddAllUsersSearchEdit() throws IOException {
		// Initialize page and table manager
		elementsPage elementsPage = new elementsPage(driver);
		WebTableManager tableManager = new WebTableManager(driver);

		elementsPage.accessElements();
		elementsPage.webTablesClick();
		testRep = extentReportManager.createTest("Test Bulk User Creation in Web Table...");
		testRep.info("Starting Test for Bulk User Creation in Web Table...");

		// Step 1: Capture initial user count
		int initialUserCount = tableManager.getUserCount();

		// Step 2: Prepare Excel data
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		String sheetName = "Sheet1";

		List<WebTableUser> users = ExcelUtils.getMappedList(filePath, sheetName, WebTableUser.class);

		// Step 3: Create all users
		elementsPage.webTablesNewRegistration();
		elementsPage.createAllUsersFromExcel(filePath, sheetName); // This adds all users
		waitForElement.isElementVisible(driver, By.id("submit"));
		elementsPage.closeFormManually();

		// Step 4: Search and edit a specific user
		String originalFirstName = "Gene"; // Name to search
		String updatedFirstName = "Genelia"; // New name to set

		elementsPage.SearcheditUserByField(originalFirstName);
		elementsPage.SearcheditFirstName(updatedFirstName);

		WebTableUser targetUser = users.stream().filter(u -> originalFirstName.equalsIgnoreCase(u.getFirstName()))
				.findFirst().orElse(null);

		if (targetUser != null) {
			targetUser.setFirstName(updatedFirstName);
			elementsPage.assertUserPresentInTable(targetUser, testRep);
			testRep.info("Updated First Name of the User is: " + updatedFirstName);
		} else {
			testRep.warning("User with first name '" + originalFirstName + "' not found in Excel data.");
		}

		// Step 5: Validate final user count
		int finalUserCount = tableManager.getUserCount();
		System.out.println("Initial user count: " + initialUserCount);
		System.out.println("Final user count: " + finalUserCount);

		testRep.pass("Finished Test for Bulk User Creation and Edit in Web Table...");
		demoqaLog.info("Test for Bulk User Creation and Edit Completed Successfully...");

	}

	@Test(priority = 34, dependsOnMethods = { "WebTablesClick" })
	public void EditSalaryDepartment() throws IOException {
		elementsPage elementsPage = new elementsPage(driver);
		WebTableManager tableManager = new WebTableManager(driver);

		elementsPage.accessElements();
		elementsPage.webTablesClick();
		testRep = extentReportManager.createTest("Test Modidfy Users Department & Salary in Web Table...");
		testRep.info("Starting Test Modidfy Users Department & Salary in Web Table...");

		// Step 1: Capture initial user count
		int initialUserCount = tableManager.getUserCount();
		System.out.println("Total Number of user before adding new ones are: " + initialUserCount);

		// Step 2: Prepare Excel data
		String filePath = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
		String sheetName = "Sheet1";

		List<WebTableUser> users = ExcelUtils.getMappedList(filePath, sheetName, WebTableUser.class);

		// Step 3: Create all users
		elementsPage.webTablesNewRegistration();
		elementsPage.createAllUsersFromExcel(filePath, sheetName); // This adds all users
		waitForElement.isElementVisible(driver, By.id("submit"));
		elementsPage.closeFormManually();
		WebTableUser user = new WebTableUser();

		// Step 4: Search and edit a specific user
		String wTFirstName = "Tom";
		int originalSalary = user.getSalary(); // Name to search
		System.out.println("Current Salary is: " + originalSalary);
		int updatedSalary = 345000; // New Salary to set
		String originalDepartment = user.getDepartment();
		String updatedDepartment = "Architecture";

		elementsPage.SearcheditUserByField(wTFirstName);
		elementsPage.SearcheditFirstName(wTFirstName);
		elementsPage.EditUsersField(wTFirstName);
		elementsPage.editSalary(updatedSalary);
		elementsPage.SearcheditUserByField(wTFirstName);
		elementsPage.SearcheditFirstName(wTFirstName);
		elementsPage.EditUsersField(wTFirstName);
		elementsPage.editDepartment(updatedDepartment);

		WebTableUser targetUser = users.stream().filter(u -> wTFirstName.equalsIgnoreCase(u.getFirstName())).findFirst()
				.orElse(null);

		if (targetUser != null) {
			targetUser.setSalary(updatedSalary);
			elementsPage.assertUserPresentInTable(targetUser, testRep);
			testRep.info("Updated Users Salary is: " + updatedSalary);
		} else {
			testRep.warning("User with first name '" + originalSalary + "' not found in Excel data.");
		}

		if (targetUser != null) {
			targetUser.setDepartment(updatedDepartment);
			elementsPage.assertUserPresentInTable(targetUser, testRep);
			testRep.info("Updated Users Department is: " + updatedDepartment);
		} else {
			testRep.warning("User with first name '" + originalDepartment + "' not found in Excel data.");
		}

		// Step 5: Validate final user count
//		int finalUserCount = tableManager.getUserCount();
//		System.out.println("Initial user count: " + initialUserCount);
//		System.out.println("Final user count: " + finalUserCount);

		testRep.pass("Finished Test for Bulk User Creation and Edit in Web Table...");
		demoqaLog.info("Test for Bulk User Creation and Edit Completed Successfully...");
	}

	@Test(priority = 35)
	public void Buttons() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Buttons Click...");
		testRep.info("Starting Test for Elements|Buttons Click...");
		elementsPage.ClickButtons();

		testRep.pass("Finished Test for Elements|Buttons Click...");
		demoqaLog.info("Test for Elements|Buttons Click Completed Successfully...");
	}

	@Test(priority = 36)
	public void DoubleClickButtons() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Buttons Click...");
		testRep.info("Starting Test for Elements|Buttons|Double Click...");
		elementsPage.ClickButtons();
		elementsPage.DoubleClickbtn();

		testRep.pass("Finished Test for Elements|Buttons|Double Click...");
		demoqaLog.info("Test for Elements|Buttons|Double Click Completed Successfully...");
	}

	@Test(priority = 37)
	public void RightClickButton() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Buttons|Right Click...");
		testRep.info("Starting Test for Elements|Buttons|Right Click...");
		elementsPage.ClickButtons();
		elementsPage.RightClickBtn();

		testRep.pass("Finished Test for Elements|Buttons|Right Click...");
		demoqaLog.info("Test for Elements|Buttons|Right Click Completed Successfully...");
	}

	@Test(priority = 38)
	public void ClickMeButton() {
		elementsPage elementsPage = new elementsPage(driver);
		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Click Me Button...");
		testRep.info("Starting Test for Elements|Buttons|Click Me Button...");
		elementsPage.ClickButtons();
		elementsPage.ClickMeBtn();

		testRep.pass("Finished Test for Elements|Buttons|Click Me Button Completed Successfully...");
		demoqaLog.info("Test for Elements|Buttons|Click Me Button Completed Successfully...");
	}

	@CaptureOnSuccess(description = "Clicked on All Buttons of Elements|Buttons page - Successfully", screenshotMode = "viewport")
	@Test(priority = 39)
	public void ClickAllButtons() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Buttons|Click All...");
		testRep.info("Starting Test for Elements|Buttons|Click All...");
		elementsPage.ClickButtons();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.DoubleClickbtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.RightClickBtn();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickMeBtn();

		testRep.pass("Finished Test for Elements|Buttons|Click All Buttons Completed Successfully...");
		demoqaLog.info("Test for Elements|Buttons|Click All Buttons Completed Successfully...");
	}

	@Test(priority = 40)
	public void ClickLinks() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links...");
		testRep.info("Starting Test for Elements|Links...");
		elementsPage.ClickLinks();
		testRep.pass("Finished Test for Elements|Links Completed Successfully...");
		demoqaLog.info("Test for Elements|Links Completed Successfully...");
	}

	@Test(priority = 41)
	public void ClickHomeLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|Home...");
		testRep.info("Starting Test for Elements|Links|Home...");
		elementsPage.ClickLinks();
		elementsPage.ClickHomeLink();
		testRep.pass("Finished Test for Elements|Links|Home Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|Home Completed Successfully...");
	}
	
	@Test(priority = 42)
	public void ClickDynamicHomeLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|Dynamic Home...");
		testRep.info("Starting Test for Elements|Links|Dynamic Home...");
		elementsPage.ClickLinks();
		elementsPage.ClickDynamicHomeLink();
		testRep.pass("Finished Test for Elements|Links|Dynamic Home Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|Dynamic Home Completed Successfully...");
	}
	
	@Test(priority = 43)
	public void ClickCreatedAPILink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|Created Link...");
		testRep.info("Starting Test for Elements|Links|Created Link...");
		elementsPage.ClickLinks();
		elementsPage.ClickCreatedAPILink();
		testRep.pass("Finished Test for Elements|Links|Created Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|Created Link Completed Successfully...");
	}
	
	@Test(priority = 44)
	public void ClickNoContentLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|No Content Link...");
		testRep.info("Starting Test for Elements|Links|No Content Link...");
		elementsPage.ClickLinks();
		elementsPage.ClickNoContentLink();
		testRep.pass("Finished Test for Elements|Links|No Content Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|No Content Link Completed Successfully...");
	}
	
	@Test(priority = 45)
	public void ClickMovedLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|Moved Link...");
		testRep.info("Starting Test for Elements|Links|Moved Link...");
		elementsPage.ClickLinks();
		elementsPage.ClickMovedLink();
		testRep.pass("Finished Test for Elements|Links|Moved Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|Moved Link Completed Successfully...");
	}
	
	@Test(priority = 46)
	public void ClickBadRequestLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|Bad Request Link...");
		testRep.info("Starting Test for Elements|Links|Bad Request Link...");
		elementsPage.ClickLinks();
		elementsPage.ClickBadRequestLink();
		testRep.pass("Finished Test for Elements|Links|Bad Request Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|Bad Request Link Completed Successfully...");
	}
	
	@Test(priority = 47)
	public void ClickUnauthorizedLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|Unathorized Link...");
		testRep.info("Starting Test for Elements|Links|Unauthorized Link...");
		elementsPage.ClickLinks();
		elementsPage.ClickUnauthorizedLink();
		testRep.pass("Finished Test for Elements|Links|Unauthorized Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|Unauthorized Link Completed Successfully...");
	}
	
	@Test(priority = 48)
	public void ClickForbiddenLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|Forbidden Link...");
		testRep.info("Starting Test for Elements|Links|Forbidden Link...");
		elementsPage.ClickLinks();
		elementsPage.ClickForbiddenLink();
		testRep.pass("Finished Test for Elements|Links|Forbidden Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|Forbidden Link Completed Successfully...");
	}
	
	@Test(priority = 49)
	public void ClickNotFoundLink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|NotFound Link...");
		testRep.info("Starting Test for Elements|Links|NotFound Link...");
		elementsPage.ClickLinks();
		elementsPage.ClickNotFoundLink();
		testRep.pass("Finished Test for Elements|Links|NotFound Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|NotFound Link Completed Successfully...");
	}
	
	@Test(priority = 50)
	public void ClickAllLinks() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Links|All Links in One GO...");
		testRep.info("Starting Test for Elements|Links|All Links in One GO...");
		elementsPage.ClickLinks();
		elementsPage.ClickHomeLink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickDynamicHomeLink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickCreatedAPILink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickNoContentLink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickMovedLink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickBadRequestLink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickUnauthorizedLink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickForbiddenLink();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPage.ClickNotFoundLink();
		testRep.pass("Finished Test for Elements|Links|All Links in One GO Completed Successfully...");
		demoqaLog.info("Test for Elements|Links|All Links in One GO Completed Successfully...");
	}
	
	@Test(priority = 51)
	public void ClickBrokenLinksImages() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Broken Links - Images...");
		testRep.info("Starting Test for Elements|Broken Links - Images...");
		elementsPage.ClickBrokenLinks();
		testRep.pass("Finished Test for Elements|Broken Links - Images Completed Successfully...");
		demoqaLog.info("Test for Elements|Broken Links - Images Completed Successfully...");
	}
	
	@Test(priority = 52)
	public void GetValidImage() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Broken Links - Images|Valid Image...");
		testRep.info("Starting Test for Elements|Broken Links - Images|Valid Image...");
		elementsPage.ClickBrokenLinks();
		elementsPage.CheckValidImage();
		testRep.pass("Finished Test for Elements|Broken Links - Images|Valid Image Completed Successfully...");
		demoqaLog.info("Test for Elements|Broken Links - Images|Valid Image Completed Successfully...");
	}
	
	@Test(priority = 53)
	public void GetBrokenImage() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Broken Links - Images|Broken Image...");
		testRep.info("Starting Test for Elements|Broken Links - Images|Broken Image...");
		elementsPage.ClickBrokenLinks();
		elementsPage.CheckBrokenImage();
		testRep.pass("Finished Test for Elements|Broken Links - Images|Broken Image Completed Successfully...");
		demoqaLog.info("Test for Elements|Broken Links - Images|Broken Image Completed Successfully...");
	}
	
	@Test(priority = 54)
	public void BrokenLinksValidlink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Broken Links - Images|Valid Link...");
		testRep.info("Starting Test for Elements|Broken Links - Images|Valid Link...");
		elementsPage.ClickBrokenLinks();
		elementsPage.ClickValidLink();
		testRep.pass("Finished Test for Elements|Broken Links - Images|Valid Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Broken Links - Images|Valid Link Completed Successfully...");
	}
	
	@Test(priority = 54)
	public void BrokenLinksBrokenlink() {
		elementsPage elementsPage = new elementsPage(driver);

		elementsPage.accessElements();
		testRep = extentReportManager.createTest("Test Elements|Broken Links - Images|Broken Link...");
		testRep.info("Starting Test for Elements|Broken Links - Images|Broken Link...");
		elementsPage.ClickBrokenLinks();
		elementsPage.ClickBrokenLink();
		testRep.pass("Finished Test for Elements|Broken Links - Images|Broken Link Completed Successfully...");
		demoqaLog.info("Test for Elements|Broken Links - Images|Broken Link Completed Successfully...");
	}

}
