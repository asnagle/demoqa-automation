package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
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

	@FindBy(xpath = "//body/div/div/div/div/div/div/div/div[1]/span[1]/div[1]")
	WebElement pageText;

	@FindBy(xpath = "//span[normalize-space()='Text Box']")
	WebElement TextBox;

	@FindBy(xpath = "//span[normalize-space()='Check Box']")
	WebElement CheckBox;

	@FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/span/button")
	WebElement ToggleHome;

	@FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/span/label/span[1]")
	WebElement HomeCheckBox;

	@FindBy(xpath = "//*[@id=\"result\"]")
	WebElement HomeCheckBoxResult;

	@FindBy(xpath = "//label[@for='tree-node-desktop']//span[@class='rct-checkbox']")
	WebElement DesktopCheckBox;

	@FindBy(xpath = "//*[@id=\"result\"]")
	WebElement DesktopCheckBoxResult;

	@FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[1]/span/button")
	WebElement ToggleDesktop;

	@FindBy(xpath = "//label[@for='tree-node-notes']//span[@class='rct-checkbox']")
	WebElement SelectNotes;

	@FindBy(xpath = "//label[@for='tree-node-commands']//span[@class='rct-checkbox']")
	WebElement SelectCommands;

	@FindBy(xpath = "//label[@for='tree-node-documents']//span[@class='rct-checkbox']")
	WebElement SelectDocuments;
	
	@FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/span/button")
	WebElement ToggleDocuments;
	
	@FindBy(xpath = "//label[@for='tree-node-workspace']//span[@class='rct-checkbox']")
	WebElement SelectWorkSpace;
	
	@FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[1]/span/button")
	WebElement ToggleWorkSpace;
	
	@FindBy(xpath = "//label[@for='tree-node-react']//span[@class='rct-checkbox']")
	WebElement SelectReact;
	
	@FindBy(xpath = "//label[@for='tree-node-angular']//span[@class='rct-checkbox']")
	WebElement SelectAngular;
	
	@FindBy(xpath = "//label[@for='tree-node-veu']//span[@class='rct-checkbox']")
	WebElement SelectVeu;
	
	@FindBy(xpath = "//label[@for='tree-node-office']//span[@class='rct-checkbox']")
	WebElement SelectOffice;
	
	@FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[2]/span/button")
	WebElement ToggleOffice;
	
	@FindBy(xpath = "//label[@for='tree-node-public']//span[@class='rct-checkbox']")
	WebElement SelectPublic;
	
	@FindBy(xpath = "//label[@for='tree-node-private']//span[@class='rct-checkbox']")
	WebElement SelectPrivate;
	
	@FindBy(xpath = "//label[@for='tree-node-classified']//span[@class='rct-checkbox']")
	WebElement SelectClassified;
	
	@FindBy(xpath = "//label[@for='tree-node-general']//span[@class='rct-checkbox']")
	WebElement SelectGeneral;
	
	@FindBy(xpath = "//label[@for='tree-node-downloads']//span[@class='rct-checkbox']")
	WebElement SelectDownloads;
	
	@FindBy(xpath = "//li[3]//span[1]//button[1]")
	WebElement ToggleDownloads;
	
	@FindBy(xpath = "//label[@for='tree-node-wordFile']//span[@class='rct-checkbox']")
	WebElement SelectWordFile;
	
	@FindBy(xpath = "//label[@for='tree-node-excelFile']//span[@class='rct-checkbox']")
	WebElement SelectExcelFile;

	@FindBy(xpath = "//input[@id='userName']")
	WebElement FullName;

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "currentAddress")
	WebElement currentAddress;

	@FindBy(id = "permanentAddress")
//	@FindBy(xpath = "//textarea[@id='permanentAddress']")
	WebElement permanentAddress;

	@FindBy(id = "submit")
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
		System.out.println("You can now Access: " + pageText.getText());
	}

	public void clickTextBox() {
		demoqaLog.info("Accessing Text Box...");
		TextBox.click();
		System.out.println(TextBox.getText());
		demoqaLog.info("You are accessing: " + TextBox.getText());
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

		List<WebElement> options = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='output']")));
		for (WebElement option : options) {
			String outputText = option.getText().trim();
			System.out.println("Information Submitted is: " + outputText);
		}
	}

	protected WebDriverWait getWait() {
		return new WebDriverWait(driver, Duration.ofSeconds(3));
	}

	public void clickCheckBox() {
		demoqaLog.info("Accessing Check Box...");
		CheckBox.click();
		System.out.println(CheckBox.getText());
		demoqaLog.info("You are accessing: " + CheckBox.getText());
	}

	public void ToggleHome() {
		demoqaLog.info("Clicking on Home Toggle Button...");
		ToggleHome.click();
	}

	public void clickHomeCheckBox() {
		demoqaLog.info("Selecting Home Check Box...");
		HomeCheckBox.click();

	}

	public void validateSelectedCheckBox() {

		List<WebElement> options = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"result\"]")));

		for (WebElement result : options) {
			String FinalResult = result.getText().trim();
			System.out.println("Information Submitted is: " + FinalResult);
			demoqaLog.info("You have Selected: " +FinalResult);
		}
	}

	public void DesktopCheckBox() {
		demoqaLog.info("Selecting Deskstop Check Box...");
		DesktopCheckBox.click();
	}

	public void ToggleDesktop() {
		demoqaLog.info("Clicking on Home Toggle Button...");
		ToggleDesktop.click();
	}

	public void selectNotesCommands() {
		demoqaLog.info("Clicking on Home Toggle Button...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleHome);
		js.executeScript("arguments[0].click();", ToggleHome);
		demoqaLog.info("Clicking on Desktop Toggle Button...");
		ToggleDesktop.click();
		demoqaLog.info("Clicking on Notes Button...");
		SelectNotes.click();
		demoqaLog.info("Clicking on Commands Button...");
		SelectCommands.click();
		demoqaLog.info("Selected Notes & Commands...");
	}

	public void DocumentsCheckbox() {
		demoqaLog.info("Selecting Documents Check Box...");
		SelectDocuments.click();
		demoqaLog.info("Selected Documents Check Box...");
	}
	
	public void ToggleDocuments() {
		demoqaLog.info("Clicking on Documents Toggle Button...");
		ToggleDocuments.click();
	}
	
	public void selectWorkSpace() {
		demoqaLog.info("Selecting WorkSpace Check Box...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", SelectWorkSpace);
		js.executeScript("arguments[0].click();", SelectWorkSpace);
		demoqaLog.info("Selected WorkSpace Check Box...");
	}
	
	public void ToggleWorkSpace() {
		demoqaLog.info("Clicking WorkSpace Toggle Button...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleWorkSpace);
		js.executeScript("arguments[0].click();", ToggleWorkSpace);
		demoqaLog.info("Clicked WorkSpace Toggle Button...");
	}
	
	public void selectReact() {
		demoqaLog.info("Selecting WorkSpace | React Check Box...");
		SelectReact.click();
		demoqaLog.info("Selected WorkSpace | React Check Box...");
	}
	
	public void selectAngular() {
		demoqaLog.info("Selecting WorkSpace | Angular Check Box...");
		SelectAngular.click();
		demoqaLog.info("Selected WorkSpace | Angular Check Box...");
	}
	
	public void selectVeu() {
		demoqaLog.info("Selecting WorkSpace | Veu Check Box...");
		SelectVeu.click();
		demoqaLog.info("Selected WorkSpace | Veu Check Box...");
	}
	
	public void selectOffice() {
		demoqaLog.info("Selecting WorkSpace Check Box...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", SelectOffice);
		js.executeScript("arguments[0].click();", SelectOffice);
		demoqaLog.info("Selected WorkSpace Check Box...");
	}
	
	public void ToggleOffice() {
		demoqaLog.info("Clicking Office Toggle Button...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleOffice);
		js.executeScript("arguments[0].click();", ToggleOffice);
		demoqaLog.info("Clicked Office Toggle Button...");
	}
	
	public void selectPublic() {
		demoqaLog.info("Selecting Office | Public Check Box...");
		SelectPublic.click();
		demoqaLog.info("Selected Office | Public Check Box...");
	}
	
	public void selectPrivate() {
		demoqaLog.info("Selecting Office | Private Check Box...");
		SelectPrivate.click();
		demoqaLog.info("Selected Office | Private Check Box...");
	}
	
	public void selectClassified() {
		demoqaLog.info("Selecting Office | Classified Check Box...");
		SelectClassified.click();
		demoqaLog.info("Selected Office | Classified Check Box...");
	}
	
	public void selectGeneral() {
		demoqaLog.info("Selecting Office | General Check Box...");
		SelectGeneral.click();
		demoqaLog.info("Selected Office | General Check Box...");
	}
	
	public void selectDownloads() {
		demoqaLog.info("Selecting Downloads Check Box...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", SelectDownloads);
		js.executeScript("arguments[0].click();", SelectDownloads);
		demoqaLog.info("Selected Downloads Check Box...");
	}
	
	public void ToggleDownloads() {
		demoqaLog.info("Clicking Downloads Toggle Button...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleDownloads);
		js.executeScript("arguments[0].click();", ToggleDownloads);
		demoqaLog.info("Clicked Downloads Toggle Button...");
	}
	
	public void selectWordFile() {
		demoqaLog.info("Selecting Downloads | Word File Check Box...");
		SelectWordFile.click();
		demoqaLog.info("Selected Downloads | Word File Check Box...");
	}
	
	public void selectExcelFile() {
		demoqaLog.info("Selecting Downloads | Excel File Check Box...");
		SelectExcelFile.click();
		demoqaLog.info("Selected Downloads | Excel File Check Box...");
	}

}
