package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.demoqaBase;
import utils.demoqaLog;

public class elementsPage extends demoqaBase {
	
	@FindBy(xpath = "//span[normalize-space()='Text Box']")
	WebElement TextBox;
	
	@FindBy(xpath = "//input[@id='userName']")
	WebElement FullName;
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "currentAddress")
	WebElement currentAddress;
	
	@FindBy(id = "permanentAddress")
//	@FindBy(xpath = "//textarea[@id='permanentAddress']")
	WebElement permanentAddress;
	
	@FindBy(id="submit")
	WebElement submitButton;
	
	
	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	public elementsPage(WebDriver driver) {
		this.driver = driver;
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		PageFactory.initElements(driver, this);
	}

	public void accessElements() {

		demoqaLog.info("Accessing Elements Card...");
		homePage homePage = new homePage(driver);
		homePage.clickElementCard();
		System.out.println("Title of this Page is: " + driver.getTitle());
	}
	
	
	public void clickTextBox() {
		demoqaLog.info("Accessing Text Box...");
		TextBox.click();
	}
	
	public void enterFullName() {
		demoqaLog.info("Entering Full Name...");
		FullName.click();
		FullName.clear();
		FullName.sendKeys("John Rambo");
		demoqaLog.info("Full Name is: " + FullName.getAttribute("value"));
	}
	
	public void enterEmail() {
		demoqaLog.info("Entering Email Address...");
		userEmail.click();
		userEmail.clear();
		userEmail.sendKeys("JRambo@demoqa.com");
		demoqaLog.info("Email Address is: " + userEmail.getAttribute("value"));
	}
	
	public void enterCurrentAddress() {
		demoqaLog.info("Entering Current Address...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", currentAddress);
		js.executeScript("arguments[0].click();", currentAddress);
		currentAddress.sendKeys("511 Grant 481, Prattsville\n Arkansas 72129\n United States");
		demoqaLog.info("Current Address is: " + currentAddress.getAttribute("value"));
//		demoqaLog.info("Current Address is: " + currentAddress.getText());
	}
	
	public void enterPermanentAddress() {
		demoqaLog.info("Entering Permanent Address...");
		permanentAddress.click();
		permanentAddress.clear();
		permanentAddress.sendKeys("579 Grant 3381, Prattsville\n Arkansas 72100\n United States");
		demoqaLog.info("Parmenant Address is: " + permanentAddress.getAttribute("value"));
	}
	
	public void submitButton() {
		demoqaLog.info("Clicking on Submit Button...");
		submitButton.click();
		demoqaLog.info("Clicked Submit Button...");
	}
	
	public void validateTextBox() {
		
		List<WebElement> options = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='output']")));
		for (WebElement option : options) {
			String outputText = option.getText().trim();
			System.out.println("Information Submitted is: " + outputText);
			}
	}
	
	protected WebDriverWait getWait() {
	    return new WebDriverWait(driver, Duration.ofSeconds(3));
	}

}
