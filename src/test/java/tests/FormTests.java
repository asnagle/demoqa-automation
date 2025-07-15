package tests;

import org.testng.annotations.Test;

import base.demoqaBase;
import pages.formsPage;
import pages.homePage;
import utils.demoqaLog;

public class FormTests extends demoqaBase{
	@Test(priority = 1)
	public void FormCard() {
		demoqaLog.info("Starting Form Card Test...");
		homePage homePage = new homePage(driver);
		homePage.clickFormCard();
		homePage.clickPracticeForm();
		System.out.println("Title of this Page is: " + driver.getTitle());
		demoqaLog.info("Form Card Test Complete...");
	}
	
	@Test(priority = 2)
	public void fillForm() {
		
		demoqaLog.info("Starting Form Filling Test...");
		formsPage formsPage = new formsPage(driver);
		formsPage.accessForms();
		formsPage.entFirstName();
		formsPage.entLastName();
		formsPage.entEmail();
		formsPage.selectGender();
		formsPage.entmobileNo();
		formsPage.entDob();
		formsPage.enterSubject();
		formsPage.selectHobbies();
		formsPage.uploadPicture();
		formsPage.enterAddress();
		formsPage.selectState();
		formsPage.selectCity();
		formsPage.submitButton();
		demoqaLog.info("Completed Form Filling Test...");
	}
	

}
