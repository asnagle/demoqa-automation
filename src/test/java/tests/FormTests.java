package tests;

import java.io.IOException;

//import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.demoqaBase;
import customAnnotations.CaptureOnSuccess;
import pages.formsPage;
import pages.homePage;
import utils.demoqaLog;
import utils.excelUtils;
import utils.extentReportManager;
import models.UserFormData;

public class FormTests extends demoqaBase {

	@DataProvider(name = "UserFormData1")
	public Object[][] getdataFormData() throws IOException {
	    String srcFile = System.getProperty("user.dir") + "/TestData/Students_Details.xlsx";
	    excelUtils.loadExcel(srcFile, "Sheet1");

	    int rowCount = excelUtils.getRowCount(); // total rows
	    Object[][] data = new Object[rowCount - 1][1]; // one UserFormData per row

	    for (int i = 1; i < rowCount; i++) { // skip header row
	        UserFormData user = new UserFormData();
	        user.setFirstName(excelUtils.getCellData(i, 0));
	        user.setLastName(excelUtils.getCellData(i, 1));
	        user.setUserMail(excelUtils.getCellData(i, 2));
	        user.setGender(excelUtils.getCellData(i, 3));
	        user.setMobile(excelUtils.getCellData(i, 4));
	        user.setDob(excelUtils.getCellData(i, 5));
	        user.setSubject(excelUtils.getCellData(i, 6));
	        user.setHobbies(excelUtils.getCellData(i, 7));
	        user.setPicturePath(excelUtils.getCellData(i, 8));
	        user.setAddress(excelUtils.getCellData(i, 9));
	        user.setState(excelUtils.getCellData(i, 10));
	        user.setCity(excelUtils.getCellData(i, 11));

	        data[i - 1][0] = user; // ✅ insert UserFormData, not the whole array
	    }

	    excelUtils.closeExcel();
	    return data;
	}

	@Test(priority = 1)
	public void FormCard() {

		testRep = extentReportManager.createTest("Test Access to Form Card");
		testRep.info("Starting test for Form Card Access");
		demoqaLog.info("Starting Form Card Test...");
		homePage homePage = new homePage(driver);
		homePage.clickFormCard();
		formsPage formsPage = new formsPage(driver);
		formsPage.clickPracticeForm();
		System.out.println("Title of this Page is: " + driver.getTitle());
		testRep.info("Title of this Page is: " + driver.getTitle());
		testRep.info("Form Card Test Completed...");
		demoqaLog.info("Form Card Test Completed...");
	}

//		================= Code for Data Driven Testing ===============
	@CaptureOnSuccess(description = "Form filled by taking data input from a Spreadsheet - Successfully", screenshotMode = "viewport")
	@Test(priority = 2, dataProvider = "UserFormData1")
	public void fillForm(models.UserFormData data) {

		testRep = extentReportManager.createTest("Test Form Filling");
		testRep.info("Starting test for Form Filling");
		demoqaLog.info("Starting Form Filling Test...");
		formsPage formsPage = new formsPage(driver);
		testRep.info("Accessing Practice Form");
		formsPage.accessForms();
		demoqaLog.info("Filling form for: " + data.firstName + " " + data.lastName);
		formsPage.entFirstName(data.firstName);
		formsPage.entLastName(data.lastName);
		formsPage.entEmail(data.userMail);// optional utility
		formsPage.selectGender(data.gender);
		formsPage.entMobileNo(data.mobile);
		formsPage.entDob(data.dob); // accepts formatted date string like dd-MM-yyyy
	    formsPage.enterSubject(data.subject);
	    formsPage.selectHobbies(data.hobbies);
	    formsPage.uploadPicture(data.picturePath);
	    formsPage.enterAddress(data.address);
	    formsPage.selectState(data.state);
	    formsPage.selectCity(data.city);
	    formsPage.submitButton();
	    testRep.pass("Test Form Filling Test Completed...");
		demoqaLog.info("Test Form Filling Completed...");
	}
	
	
	
}
