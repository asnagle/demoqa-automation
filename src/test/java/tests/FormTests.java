package tests;

import org.testng.annotations.Test;

import base.demoqaBase;
import pages.formsPage;
import pages.homePage;

public class FormTests extends demoqaBase{
//	@Test(priority = 1)
//	public void FormCard() {
//		
//		homePage homePage = new homePage(driver);
//		homePage.clickFormCard();
//		homePage.clickPracticeForm();
//		System.out.println("Title of this Page is: " + driver.getTitle());
//		
//	}
	
	@Test(priority = 1)
	public void fillForm() {

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
		
	}
	

}
