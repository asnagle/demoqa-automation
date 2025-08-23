package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import com.aventstack.extentreports.ExtentTest;
import base.demoqaBase;
import models.WebTableUser;
import utils.ExcelUtils;
import utils.FetchLinkResponse;
import utils.FileDownloadValidator;
import utils.JSclick;
import utils.RetryUrlAccess;
//import utils.demoqaLog;

public class elementsPage extends demoqaBase {

	@FindBy(xpath = "//div[contains(@class,'col-12 mt-4 col-md-6')]")
	WebElement Cardpage;
	
	@FindBy(xpath = "//body/div/div/div/div/div/div/div/div[1]/span[1]/div[1]")
	WebElement pageText;

	@FindBy(xpath = "//span[normalize-space()='Text Box']")
	WebElement TextBox;
	
	@FindBy(xpath = "//h1[normalize-space()='Text Box']")
	WebElement TextBoxPage;
	
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
	WebElement permanentAddress;

	@FindBy(id = "submit")
	WebElement submitButton;

	@FindBy(xpath = "//*[@id=\"item-2\"]")
	WebElement radioButton;

	@FindBy(xpath = "//h1[normalize-space()='Radio Button']")
	WebElement radioPageTitle;

	@FindBy(xpath = "//input[@id='yesRadio']")
	WebElement yesRadio;

	@FindBy(xpath = "//input[@id='impressiveRadio']")
	WebElement impressiveRadio;

	@FindBy(xpath = "//span[@class='text-success']")
	WebElement radioButtonSelected;

	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-3']")
	WebElement webTables;

	@FindBy(xpath = "//button[@id='addNewRecordButton']")
	WebElement webTableAdd;

	@FindBy(xpath = "//input[@id='firstName']")
	WebElement wTableFirstName;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement wTableLastName;

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement wTableUserEmail;

	@FindBy(xpath = "//input[@id='age']")
	WebElement wTableAge;

	@FindBy(xpath = "//input[@id='salary']")
	WebElement wTableSalary;

	@FindBy(xpath = "//input[@id='department']")
	WebElement wTableDept;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement wTableSubmitBtn;

	@FindBy(xpath = "//input[@id='searchBox']")
	WebElement wTablesSearchBox;

	@FindBy(xpath = "//button[@class='close']")
	WebElement wTableFormCloseBtn;

	@FindBy(xpath = "//span[normalize-space()='Buttons']")
	WebElement Buttons;

	@FindBy(xpath = "//button[@id='doubleClickBtn']")
	WebElement doubleClickBtn;

	@FindBy(xpath = "//button[@id='rightClickBtn']")
	WebElement rightClickBtn;

	@FindBy(xpath = "//button[text()='Click Me' and contains(@class, 'btn-primary')]")
	WebElement ClickMeBtn;

	@FindBy(xpath = "//span[normalize-space()='Links']")
	WebElement Links;

	@FindBy(xpath = "//a[@id='simpleLink']")
	WebElement HomeLink;

	@FindBy(xpath = "//a[@id='dynamicLink']")
	WebElement DynamicHomeLink;

	@FindBy(xpath = "//*[@id='created']")
	WebElement CreatedAPI;

	@FindBy(xpath = "//*[@id=\"no-content\"]")
	WebElement NoContent;

	@FindBy(xpath = "//*[@id='moved']")
	WebElement MovedLink;

	@FindBy(xpath = "//*[@id='bad-request']")
	WebElement BadRequestLink;

	@FindBy(xpath = "//*[@id='unauthorized']")
	WebElement UnAuthorizedLink;

	@FindBy(xpath = "//*[@id=\"forbidden\"]")
	WebElement ForbiddenLink;

	@FindBy(xpath = "//*[@id='invalid-url']")
	WebElement NotFoundLink;

	@FindBy(xpath = "//p[@id='linkResponse']")
	WebElement LinkResponse;

	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-6']")
	WebElement BrokenLinksImages;

	@FindBy(xpath = "//p[normalize-space()='Valid image']")
	WebElement ValidImage;

	@FindBy(xpath = "//p[normalize-space()='Broken image']")
	WebElement BrokenImage;

	@FindBy(xpath = "//h1[normalize-space()='Broken Links - Images']")
	WebElement BrokenLinksPageTitle;

	@FindBy(xpath = "//a[normalize-space()='Click Here for Valid Link']")
	WebElement BrokenLinksValidLink;

	@FindBy(xpath = "//header//img[contains(@src, 'Toolsqa.jpg')]")
	WebElement ValidLinkPage;

	@FindBy(xpath = "//a[normalize-space()='Click Here for Broken Link']")
	WebElement BrokenLinksBrokenLink;

	@FindBy(xpath = "//div[@id='content']//p[contains(text(), '500 status code')]")
	WebElement BrokenLinksBrokenLinkPage;

	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-7']")
	WebElement UploadDownloadBtn;

	@FindBy(xpath = "//*[@id='downloadButton']")
	WebElement DownloadBtn;

	@FindBy(xpath = "//input[@id='uploadFile']")
	WebElement UploadBtn;
	
	@FindBy(xpath = "//p[@id='uploadedFilePath']")
	WebElement UploadedFilePath;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-8']")
	WebElement DynamicProp;
	
	@FindBy(xpath = "//h1[normalize-space()='Dynamic Properties']")
	WebElement DynamicPropPage;
	
	@FindBy(xpath = "//button[@id='enableAfter']")
	WebElement EnableAfter;
	
	@FindBy(xpath = "//button[@id='colorChange']")
	WebElement ColorChange;
	
	@FindBy(xpath = "//button[@id='visibleAfter']")
	WebElement VisibleAfter;

	private String wTFirstname;
	private String wTLastname;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	public elementsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		PageFactory.initElements(driver, this);
		System.out.println("Instance Variables Declared are: " + wTFirstname + wTLastname);
	}

	public void accessElements() {

		demoqaLog.info("Accessing Elements Card...");
		homePage homePage = new homePage(driver);
		RetryUrlAccess.navigateWithRetry(driver, "https://demoqa.com", 3);
		homePage.clickElementCard();
		System.out.println("Title of this Page is: " + driver.getTitle());
		System.out.println("You can now Access: " + pageText.getText());
		System.out.println("Page Text is: " + Cardpage.getText());
		String cardpage = Cardpage.getText();

		Assert.assertEquals("Please select an item from left to start practice.", cardpage);
	}

	public void clickTextBox() {
		demoqaLog.info("Accessing Text Box...");
		TextBox.click();
		String textBoxPage = TextBoxPage.getText();
		System.out.println(textBoxPage);
		demoqaLog.info("You are accessing: " + textBoxPage);
		Assert.assertEquals("Text Box", textBoxPage);
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
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", currentAddress);
//		js.executeScript("arguments[0].click();", currentAddress);
		JSclick.scrollAndClick(driver, currentAddress);
		currentAddress.sendKeys("511 Grant 481, Prattsville\n Arkansas 72129\n United States");
		demoqaLog.info("Current Address is: " + currentAddress.getAttribute("value"));
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
		return new WebDriverWait(driver, Duration.ofSeconds(10));
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
			demoqaLog.info("You have Selected: " + FinalResult);
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
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", ToggleHome);
//		js.executeScript("arguments[0].click();", ToggleHome);
		JSclick.scrollAndClick(driver, ToggleHome);
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
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", SelectWorkSpace);
//		js.executeScript("arguments[0].click();", SelectWorkSpace);
		JSclick.scrollAndClick(driver, SelectWorkSpace);
		demoqaLog.info("Selected WorkSpace Check Box...");
	}

	public void ToggleWorkSpace() {
		demoqaLog.info("Clicking WorkSpace Toggle Button...");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", ToggleWorkSpace);
//		js.executeScript("arguments[0].click();", ToggleWorkSpace);
		JSclick.scrollAndClick(driver, ToggleWorkSpace);
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
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", SelectOffice);
//		js.executeScript("arguments[0].click();", SelectOffice);
		JSclick.scrollAndClick(driver, SelectOffice);
		demoqaLog.info("Selected WorkSpace Check Box...");
	}

	public void ToggleOffice() {
		demoqaLog.info("Clicking Office Toggle Button...");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", ToggleOffice);
//		js.executeScript("arguments[0].click();", ToggleOffice);
		JSclick.scrollAndClick(driver, ToggleOffice);
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Declared WebDriverWait: " + wait);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", SelectClassified);
//		js.executeScript("arguments[0].click();", SelectClassified);
		JSclick.scrollAndClick(driver, SelectClassified);
		demoqaLog.info("Selected Office | Classified Check Box...");
	}

	public void selectGeneral() {
		demoqaLog.info("Selecting Office | General Check Box...");
		SelectGeneral.click();
		demoqaLog.info("Selected Office | General Check Box...");
	}

	public void selectDownloads() {
		demoqaLog.info("Selecting Downloads Check Box...");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", SelectDownloads);
//		js.executeScript("arguments[0].click();", SelectDownloads);
		JSclick.scrollAndClick(driver, SelectDownloads);
		demoqaLog.info("Selected Downloads Check Box...");
	}

	public void ToggleDownloads() {
		demoqaLog.info("Clicking Downloads Toggle Button...");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", ToggleDownloads);
//		js.executeScript("arguments[0].click();", ToggleDownloads);
		JSclick.scrollAndClick(driver, ToggleDownloads);
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

	public void radioButtonClick() {
		demoqaLog.info("Clicking on Radio Button...");
		radioButton.click();
	}

	public void validateRadioButtonClick() {
		demoqaLog.info("Validating Radio Button Click...");
		String radioPage = radioPageTitle.getText();
		System.out.println("Page Title is: " + radioPage);
	}

	public void yesRadioSelect() {
		demoqaLog.info("Selecting on Yes Radio Button...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Declared WebDriverWait: " + wait);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", yesRadio);
//		js.executeScript("arguments[0].click();", yesRadio);
		JSclick.scrollAndClick(driver, yesRadio);
	}

	public void validateRadioSelection() {
		demoqaLog.info("Validating Radio Selection Test Result...");
		String selectedRadioButton = radioButtonSelected.getText();
		if (selectedRadioButton.equalsIgnoreCase("Yes") | (selectedRadioButton.equalsIgnoreCase("Impressive"))) {
			demoqaLog.info("You have selected: " + radioButtonSelected.getText());
			demoqaLog.info("Test Case of Radio Button Selection PASSED...");

		}
	}

	public void impressiveRadioSelect() {
		demoqaLog.info("Selecting on Impressive Radio Button...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Declared WebDriverWait: " + wait);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", impressiveRadio);
//		js.executeScript("arguments[0].click();", impressiveRadio);
		JSclick.scrollAndClick(driver, impressiveRadio);
	}

	public void webTablesClick() {
		demoqaLog.info("Click on Elements|Web Tables...");
		webTables.click();
	}

	public void webTablesNewRegistration() {
		demoqaLog.info("Click on Elements|Web Tables|Registration Form...");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", webTableAdd);
//		js.executeScript("arguments[0].click();", webTableAdd);
		JSclick.scrollAndClick(driver, webTableAdd);
	}

	public void createSingleUserFromExcel(String filePath, String sheetName) {
		WebTableUser user = ExcelUtils.getFirstUserFromExcel(filePath, sheetName);
		fillWebTableForm(user);
	}

	public void fillWebTableForm(WebTableUser user) {
		try {
//	        demoqaLog.info("Registering user: {}", user.getFirstName());
			demoqaLog.error("Registering User: " + user.getFirstName());

			wTAddFirstName(user.getFirstName());
			wTAddLastName(user.getLastName());
			wTAddUserEmail(user.getEmail());
			wTAddAge(user.getAge()); // Already parsed safely
			wTAddSalary(user.getSalary()); // Already parsed safely
			wTAddDepartment(user.getDepartment());

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(wTableSubmitBtn));
			wTSubmitBtn();

//	        demoqaLog.info("User submitted: ", user.getFirstName());
			demoqaLog.error("Submitted to register user: " + user.getFirstName());
		} catch (Exception e) {
			demoqaLog.error("Failed to register user:" + user.getFirstName());
			throw e; // or continue if you want to skip failed users
		}
	}

	public void wTAddFirstName(String firstname) {
		demoqaLog.info("Click on Elements|Web Tables|Registration Form|FirstName...");
		wTableFirstName.click();
		wTableFirstName.clear();
		wTableFirstName.sendKeys(firstname);
		demoqaLog.info("First Name entered is: " + wTableFirstName.getAttribute("value"));
		this.wTFirstname = firstname;
	}

	public void wTAddLastName(String lastname) {
		demoqaLog.info("Click on Elements|Web Tables|Registration Form|LastName...");
		System.out.println("Last Name is: " + lastname);
		wTableLastName.click();
		wTableLastName.clear();
		wTableLastName.sendKeys(lastname);
		demoqaLog.info("Last Name entered is: " + wTableLastName.getAttribute("value"));
		this.wTLastname = lastname;
	}

	public void wTAddUserEmail(String email) {
		demoqaLog.info("Click on Elements|Web Tables|Registration Form|Email...");
		System.out.println("Email ID is: " + email);
		wTableUserEmail.click();
		wTableUserEmail.clear();
		wTableUserEmail.sendKeys(email);
		demoqaLog.info("Email entered is: " + wTableUserEmail.getAttribute("value"));
	}

	public void wTAddAge(int age) {
		demoqaLog.info("Click on Elements|Web Tables|Registration Form|Age...");
		wTableAge.click();
		wTableAge.clear();
		wTableAge.sendKeys(String.valueOf(age));
		demoqaLog.info("Age entered is: " + wTableAge.getAttribute("value"));
	}

	public void wTAddSalary(int salary) {
		demoqaLog.info("Click on Elements|Web Tables|Registration Form|Salary...");
		wTableSalary.click();
		wTableSalary.clear();
		wTableSalary.sendKeys(String.valueOf(salary));
		demoqaLog.info("Salary entered is: " + wTableSalary.getAttribute("value"));
	}

	public void wTAddDepartment(String Department) {
		demoqaLog.info("Click on Elements|Web Tables|Registration Form|Department...");
		wTableDept.click();
		wTableDept.clear();
		wTableDept.sendKeys(Department);
		demoqaLog.info("Department entered is: " + wTableDept.getAttribute("value"));
	}

	public void wTSubmitBtn() {
		demoqaLog.info("Submitting Registration Form...");
		wTableSubmitBtn.click();
		demoqaLog.info("Registration Form Submitted...");
	}

	public void closeFormManually() {
//	    WebElement closeBtn = driver.findElement(By.cssSelector(".modal-close"));
		if (wTableFormCloseBtn.isDisplayed()) {
			wTableFormCloseBtn.click();
		}
	}

	public void wTSearchBox(String FirstName) {
		demoqaLog.info("Searching by First Name...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(wTablesSearchBox));

		wTablesSearchBox.click();
		wTablesSearchBox.clear();
		wTablesSearchBox.sendKeys(FirstName);
	}

	public void createAllUsersFromExcel(String filePath, String sheetName) throws IOException {
		List<WebTableUser> users = ExcelUtils.getUserListFromExcel(filePath, sheetName); // ✅ Correct method
		for (WebTableUser user : users) {
			fillWebTableForm(user); // Populate form fields
//	      wTSubmitBtn();                  // Optional submit button
			webTablesNewRegistration(); // Click "Add" button to submit
		}
	}

	public int getWebTableRowCount() {
		// Adjust selector based on your actual table structure
		List<WebElement> rows = driver.findElements(By.cssSelector("div.rt-tbody div.rt-tr-group"));
		return rows.size();
	}

	public void assertUserPresentInTable(WebTableUser user, ExtentTest extentTest) {
		extentTest.info("Searching for user: " + user.getFirstName());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		By resultLocator = By.xpath("//div[@class='rt-td' and text()='" + user.getFirstName() + "']");

		try {
			WebElement resultCell = wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));
			Assert.assertTrue(resultCell.isDisplayed(), "User not found in web table after search.");
			extentTest.pass("User '" + user.getFirstName() + "' found in web table.");
		} catch (Exception e) {
			extentTest.fail("User '" + user.getFirstName() + "' not found in web table. Exception: " + e.getMessage());
			throw e;
		}
	}

	public void assertUserNotPresentInTable(WebTableUser user, ExtentTest extentTest) {
		String firstName = user.getFirstName();
		extentTest.info("Verifying absence of user: " + firstName);

		By resultLocator = By.xpath("//div[@class='rt-td' and text()='" + firstName + "']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {
			boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(resultLocator));
			Assert.assertTrue(isInvisible, "User '" + firstName + "' is still visible in the web table.");
			extentTest.pass("User '" + firstName + "' is not present in the web table as expected.");
		} catch (org.openqa.selenium.TimeoutException te) {
			extentTest.fail("User '" + firstName + "' is still present in the web table.");
			throw te;
		} catch (Exception e) {
			extentTest.fail("Unexpected error while verifying user absence: " + e.getMessage());
			throw e;
		}
	}

	public void editUserByField(String wTFirstname) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + wTFirstname + "']]")));

		String xpath = String.format("//div[@class='rt-tr-group'][.//div[text()='%s']]//span[@title='Edit']",
				wTFirstname);
		WebElement editButton = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
		editButton.click();
	}

	public void SearcheditUserByField(String wTFirstname) {
		wTablesSearchBox.click();
		wTablesSearchBox.clear();
		wTablesSearchBox.sendKeys(wTFirstname);

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + wTFirstname + "']]")));

//		WebElement editButton = driver.findElement(By.id("edit-record-4"));
//		WebElement editButton = driver.findElement(By.xpath("//*[@id=\"edit-record-4\"]"));
		String xpath = String.format("//div[@class='rt-tr-group'][.//div[text()='%s']]//span[@title='Edit']",
				wTFirstname);
		WebElement editButton = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
		editButton.click();
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
//		editButton.click();
	}

	public void EditUsersField(String wTFieldname) {
//		wTablesSearchBox.click();
//		wTablesSearchBox.clear();
//		wTablesSearchBox.sendKeys(wTFirstname);

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + wTFieldname + "']]")));

		String xpath = String.format("//div[@class='rt-tr-group'][.//div[text()='%s']]//span[@title='Edit']",
				wTFieldname);
		WebElement editButton = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
		editButton.click();
	}

	public void editFirstName(String oldName) {
		demoqaLog.info("Modifying First Name of the user...");

		// Clear and update the input field
		wTableFirstName.clear();
		String updatedName = "Tonny";
		wTableFirstName.sendKeys(updatedName);
		wTableSubmitBtn.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editSalary(int updatedSalary) {
		demoqaLog.info("Updating Users Salary...");

		// Clear and update the input field
		System.out.println("New Salary is: " + updatedSalary);
		wTableSalary.clear();
		String newSalary = String.valueOf(updatedSalary);
		wTableSalary.sendKeys(newSalary);
		wTableSubmitBtn.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Wait for the updated value to appear in the table
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By updatedNameLocator = By.xpath("//div[normalize-space()='" + updatedSalary + "']");
		WebElement updatedNameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(updatedNameLocator));

		demoqaLog.info("Updated Salarye is: " + updatedNameCell.getText());
	}

	public void editDepartment(String updatedDepartment) {
		demoqaLog.info("Updating Users Department...");

		// Clear and update the input field
		System.out.println("New Salary is: " + updatedDepartment);
		wTableDept.clear();
		String newDepartment = updatedDepartment;
		wTableDept.sendKeys(newDepartment);
		wTableSubmitBtn.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Wait for the updated value to appear in the table
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By updatedNameLocator = By.xpath("//div[normalize-space()='" + newDepartment + "']");
		WebElement updatedNameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(updatedNameLocator));

		demoqaLog.info("Updated Salarye is: " + updatedNameCell.getText());
	}

	public void SearcheditFirstName(String updatedFirstName) {
		demoqaLog.info("Modifying First Name of the user...");

		// Clear and update the input field
		wTableFirstName.clear();
		wTableFirstName.sendKeys(updatedFirstName);
		wTableSubmitBtn.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wTablesSearchBox.clear();
		wTablesSearchBox.sendKeys(updatedFirstName);

		// Wait for the updated value to appear in the table
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By updatedNameLocator = By.xpath("//div[normalize-space()='" + updatedFirstName + "']");
		WebElement updatedNameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(updatedNameLocator));

		demoqaLog.info("Updated First Name is: " + updatedNameCell.getText());
	}

	public void DeleteUser(String wTFirstname) {
		WebElement deleteUser = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"delete-record-4\"]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteUser);
		deleteUser.click();
	}

	public void ClickButtons() {
		demoqaLog.info("Clicking on Elements|Buttons...");
		Buttons.click();
		demoqaLog.info("Clicked on Elements|Buttons...");
	}

	public void DoubleClickbtn() {
		demoqaLog.info("Clicking on Elements|Buttons|Double Click button...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Declared WebDriverWait: " + wait);
		new Actions(driver).doubleClick(doubleClickBtn).perform();
		String result = driver.findElement(By.xpath("//p[@id='doubleClickMessage']")).getText();
		System.out.println(result);
		Assertion assertion = new Assertion();
		assertion.assertEquals("You have done a double click",
				driver.findElement(By.xpath("//p[@id='doubleClickMessage']")).getText());
		demoqaLog.info("Clicked on Elements|Buttons|Double Click button..."
				+ driver.findElement(By.xpath("//p[@id='doubleClickMessage']")).getText());
	}

	public void RightClickBtn() {
		demoqaLog.info("Clicking on Elements|Buttons|Right Click button...");
		WebElement RightClickbtn = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='rightClickBtn']")));
		System.out.println("Wating for Element: " + RightClickbtn);
		new Actions(driver).contextClick(rightClickBtn).perform();

		Assert.assertEquals("You have done a right click",
				driver.findElement(By.xpath("//p[@id='rightClickMessage']")).getText());
		demoqaLog.info("Clicked on Elements|Buttons|Right Click button..."
				+ driver.findElement(By.xpath("//p[@id='rightClickMessage']")).getText());
	}

	public void ClickMeBtn() {
		demoqaLog.info("Clicking on Elements|Buttons|Click Me button...");

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", ClickMeBtn);
//		js.executeScript("arguments[0].click();", ClickMeBtn);
		JSclick.scrollAndClick(driver, ClickMeBtn);

		Assert.assertEquals("You have done a dynamic click",
				driver.findElement(By.xpath(
						"//p[normalize-space(text())='You have done a dynamic click' and @id='dynamicClickMessage']"))
						.getText());
		demoqaLog.info("Clicked on Elements|Buttons|Click Me button..." + driver
				.findElement(By.xpath(
						"//p[normalize-space(text())='You have done a dynamic click' and @id='dynamicClickMessage']"))
				.getText());
	}

	public void ClickLinks() {
		demoqaLog.info("Clicking on Elements|Links|Home Link...");
		Links.click();
		demoqaLog.info("Clicked on Elements|Links|Home Link...");
	}

	public void ClickHomeLink() {
		demoqaLog.info("Clicking on Elements|Links|Home Link...");
//		HomeLink.click();
		JSclick.scrollAndClick(driver, HomeLink);
		demoqaLog.info("Clicked on Elements|Links|Home Link...");
	}

	public void ClickDynamicHomeLink() {
		demoqaLog.info("Clicking on Elements|Links|DynamicHome Link...");
//		DynamicHomeLink.click();
		JSclick.scrollAndClick(driver, DynamicHomeLink);
		demoqaLog.info("Clicked on Elements|Links|DynamicHome Link...");
	}

	public void ClickCreatedAPILink() {
		demoqaLog.info("Clicking on Elements|Links|Created Link...");
		WebElement Createdlink = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='created']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", Createdlink);
//		js.executeScript("arguments[0].click();", CreatedAPI);
		JSclick.scrollAndClick(driver, CreatedAPI);
		demoqaLog.info("Clicked on Elements|Links|Created Link...");
		String linkResponse = FetchLinkResponse.fetchLinkResponseText(driver, CreatedAPI);

		Assert.assertEquals("Created", linkResponse);
		demoqaLog.info("Clicked on Elements|Links|Created Link response is: " + linkResponse);
	}

	public void ClickNoContentLink() {
		demoqaLog.info("Clicking on Elements|Links|No Content Link...");
		WebElement NoContentlink = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"no-content\"]")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", NoContentlink);
//		js.executeScript("arguments[0].click();", NoContent);
		JSclick.scrollAndClick(driver, NoContent);

		demoqaLog.info("Clicked on Elements|Links|No Content Link...");
		String linkResponse = FetchLinkResponse.fetchLinkResponseText(driver, NoContent);

		Assert.assertEquals("No Content", linkResponse);
		demoqaLog.info("Clicked on Elements|Links|No Content Link response is: " + linkResponse);
	}

	public void ClickMovedLink() {
		demoqaLog.info("Clicking on Elements|Links|Moved Link...");
		WebElement Movedlink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='moved']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", Movedlink);
//		js.executeScript("arguments[0].click();", MovedLink);
		JSclick.scrollAndClick(driver, MovedLink);

		demoqaLog.info("Clicked on Elements|Links|Moved Link...");

		String linkResponse = FetchLinkResponse.fetchLinkResponseText(driver, Movedlink);

		Assert.assertEquals("Moved", linkResponse);
		demoqaLog.info("Clicked on Elements|Links|Moved Link Link response is: " + linkResponse);
	}

	public void ClickBadRequestLink() {
		demoqaLog.info("Clicking on Elements|Links|Bad Request Link...");
		WebElement BadRequestlink = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='bad-request']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", BadRequestlink);
//		js.executeScript("arguments[0].click();", BadRequestLink);
		JSclick.scrollAndClick(driver, BadRequestLink);

		demoqaLog.info("Clicked on Elements|Links|Bad Request Link...");
		String linkResponse = FetchLinkResponse.fetchLinkResponseText(driver, BadRequestlink);

		Assert.assertEquals("Bad Request", linkResponse);
		demoqaLog.info("Clicked on Elements|Links|Bad Request Link response is: " + linkResponse);
	}

	public void ClickUnauthorizedLink() {
		demoqaLog.info("Clicking on Elements|Links|Unauthorized Link...");
		WebElement Unauthorizedlink = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"unauthorized\"]")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", Unauthorizedlink);
//		js.executeScript("arguments[0].click();", UnAuthorizedLink);
		JSclick.scrollAndClick(driver, UnAuthorizedLink);

		demoqaLog.info("Clicked on Elements|Links|Unauthorized Link...");
		String linkResponse = FetchLinkResponse.fetchLinkResponseText(driver, UnAuthorizedLink);

		Assert.assertEquals("Unauthorized", linkResponse);
		demoqaLog.info("Clicked on Elements|Links|Unauthorized Link response is: " + linkResponse);
	}

	public void ClickForbiddenLink() {
		demoqaLog.info("Clicking on Elements|Links|Forbidden Link...");
		WebElement Forbiddenlink = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"forbidden\"]")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", Forbiddenlink);
//		js.executeScript("arguments[0].click();", ForbiddenLink);
		JSclick.scrollAndClick(driver, ForbiddenLink);

		demoqaLog.info("Clicked on Elements|Links|Forbidden Link...");
		String linkResponse = FetchLinkResponse.fetchLinkResponseText(driver, Forbiddenlink);

		Assert.assertEquals("Forbidden", linkResponse);
		demoqaLog.info("Clicked on Elements|Links|Forbidden Link response is: " + linkResponse);
	}

	public void ClickNotFoundLink() {
		demoqaLog.info("Clicking on Elements|Links|NotFound Link...");
		WebElement NotFoundlink = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='invalid-url']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", NotFoundlink);
//		js.executeScript("arguments[0].click();", NotFoundLink);
		JSclick.scrollAndClick(driver, NotFoundLink);

		demoqaLog.info("Clicked on Elements|Links|NotFound Link...");
		String linkResponse = FetchLinkResponse.fetchLinkResponseText(driver, NotFoundlink);
		Assert.assertEquals("Not Found", linkResponse);
		demoqaLog.info("Clicked on Elements|Links|Not Found Link response is: " + linkResponse);
	}

	public void ClickBrokenLinks() {
		demoqaLog.info("Clicking on Elements|Broken Links - Images...");
//		BrokenLinksImages.click();
		WebElement Brokenlink = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//span[normalize-space()='Broken Links - Images']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", Brokenlink);
//		js.executeScript("arguments[0].click();", BrokenLinksImages);
		JSclick.scrollAndClick(driver, BrokenLinksImages);
		
		demoqaLog.info("Clicked on Elements|Broken Links - Images...");
		System.out.println("Page Title is: " + BrokenLinksPageTitle.getText());
		Assert.assertEquals("Broken Links - Images", BrokenLinksPageTitle.getText());
	}

	public void CheckValidImage() {
		demoqaLog.info("Checking for Elements|Broken Links|Valid Image...");
		WebElement validimage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Valid image']")));
		System.out.println("Found WebElement of: " + validimage.getText());
		Assert.assertEquals("Valid image", ValidImage.getText());
		demoqaLog.info("Checked Elements|Broken Links|Valid Image " + ValidImage.getText());
	}

	public void CheckBrokenImage() {
		demoqaLog.info("Checking for Elements|Broken Links|Valid Image...");
		WebElement brokenimage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[normalize-space()='Broken image']")));
		System.out.println("Found WebElement of: " + brokenimage.getText());

		Assert.assertEquals("Broken image", BrokenImage.getText());
		demoqaLog.info("Checked Elements|Broken Links|Broken Image " + BrokenImage.getText());
	}

	public void ClickValidLink() {
		demoqaLog.info("Checking for Elements|Broken Links|Valid Link...");
		WebElement brkvalidlink = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[normalize-space()='Click Here for Valid Link']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", brkvalidlink);
//		js.executeScript("arguments[0].click();", BrokenLinksValidLink);
		JSclick.scrollAndClick(driver, BrokenLinksValidLink);
		
		demoqaLog.info("Clicked on Elements|Broken Links - Images|Valid Link...");

		WebElement logo = driver.findElement(By.xpath("//header//img[contains(@src, 'Toolsqa.jpg')]"));
		Assert.assertTrue(logo.isDisplayed(), "Logo image is not visible");
		System.out.println("Logo of Valid Link Clicked " + logo.getAttribute("src"));
		demoqaLog.info("Logo source: " + logo.getAttribute("src"));
	}

	public void ClickBrokenLink() {
		demoqaLog.info("Checking for Elements|Broken Links|Broken Link...");
		WebElement brokenlink = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[normalize-space()='Click Here for Broken Link']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", brokenlink);
//		js.executeScript("arguments[0].click();", BrokenLinksBrokenLink);
		JSclick.scrollAndClick(driver, BrokenLinksBrokenLink);
		
		demoqaLog.info("Clicked on Elements|Broken Links - Images|Broken Link...");
		String URLresponse = BrokenLinksBrokenLinkPage.getText();
		System.out.println("Broken Link Response is: " + URLresponse);
		WebElement statusMessage = driver.findElement(By.xpath("//*[contains(text(), '500 status code')]"));
		Assert.assertTrue(statusMessage.isDisplayed(), "Expected 500 status code message is not displayed");

		demoqaLog.info("Broken Link Response is: " + URLresponse);
	}

	public void ClickUploadDownload() {
		demoqaLog.info("Checking for Elements|Upload and Download|Download Button...");
		WebElement uploaddownloadbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='element-list collapse show']//li[@id='item-7']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", uploaddownloadbtn);
//		js.executeScript("arguments[0].click();", UploadDownloadBtn);
		JSclick.scrollAndClick(driver, UploadDownloadBtn);
		
		demoqaLog.info("Clicked on Elements|Upload and Download...");
	}

	public void DownloadFile() {
		demoqaLog.info("Clicking on Elements|Upload and Download|Download Button...");
		String downloadDir = "C:\\Users\\dell\\Downloads";
		String expectedFileName = "sampleFile.jpeg";
		String filePath = "C:\\Users\\dell\\Downloads\\sampleFile.jpeg";

//		Cleanup existing file for successful download

		File file = new File(filePath);
		if (file.exists()) {
			System.out.println("Deleting Existing file before download...");
			file.delete();
		}
//		Cleanup completed

		WebElement downloadbtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='downloadButton']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", downloadbtn);
//		js.executeScript("arguments[0].click();", DownloadBtn);
		JSclick.scrollAndClick(driver, DownloadBtn);
		
		demoqaLog.info("Clicked on Elements|Upload and Download|Download Button...");

		System.out.println("Downloaded file is: " + downloadDir + expectedFileName);

		boolean isDownloaded = FileDownloadValidator.isFileDownloaded(downloadDir, expectedFileName);
		Assert.assertTrue(isDownloaded, "File was not downloaded successfully.");
	}

	public void UploadFile() {
		demoqaLog.info("Checking for Elements|Upload and Download|Upload Button...");
		String uploadFile = "Ophoto.jpg";

		try {
			if (uploadFile == null || uploadFile.trim().isEmpty()) {
				System.err.println("Photo path is empty in test data.");
				return;
			}

			String fullPath = System.getProperty("user.dir") + "\\"+ "TestData";
			System.out.println("File being Uploaded is: " + fullPath);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uploadFile")));

			wait.until(ExpectedConditions.elementToBeClickable(UploadBtn));
			String Uploadthis = fullPath+"\\"+uploadFile;
			System.out.println("Uploading: " +Uploadthis);
			UploadBtn.sendKeys(Uploadthis);
			System.out.println("Uploaded photo: " + fileInput);
		} catch (Exception e) {
			System.err.println("Photo upload failed.");
			e.printStackTrace();
		}
		
		WebElement uploadStatus = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='uploadedFilePath']")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", uploadStatus);

		demoqaLog.info("Clicked on Elements|Upload and Download|Upload Button...");
		String uploadedFilePath = FetchLinkResponse.fetchLinkResponseText(driver, UploadedFilePath);
		Assert.assertEquals("C:\\fakepath\\Ophoto.jpg", uploadedFilePath);
		demoqaLog.info("Clicked on Elements|Upload and Download|Upload response is: " + uploadedFilePath);
	}
	
	public void ClickDynamicProperties() {
		demoqaLog.info("Checking for Elements|Dynamic Properties Button...");
		WebElement dynamicPropertiesbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='element-list collapse show']//li[@id='item-8']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", dynamicPropertiesbtn);
//		js.executeScript("arguments[0].click();", DynamicProp);
		JSclick.scrollAndClick(driver, DynamicProp);
		
		demoqaLog.info("Clicked on Elements|Dynamic Properties...");
		String DynProp = DynamicPropPage.getText();
		System.out.println("Dynamic Properties Page Tile is: " + DynProp);
		Assert.assertEquals("Dynamic Properties", DynProp);
	}
	
	public void ClickWillEnable() {
		demoqaLog.info("Checking for Elements|Dynamic Properties|Will Enable 5 sec Button...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement willEnablebtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@id='enableAfter']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", willEnablebtn);
//		js.executeScript("arguments[0].click();", EnableAfter);
		JSclick.scrollAndClick(driver, EnableAfter);
		
		demoqaLog.info("Clicked on Elements|Dynamic Properties|Will Enable 5 sec Button...");
		String buttonTxt = EnableAfter.getText();
		System.out.println("Clicked on: " + buttonTxt);
		Assert.assertEquals("Will enable 5 seconds", buttonTxt);
	}
	
	public void ClickColorChange() {
		demoqaLog.info("Checking for Elements|Dynamic Properties|Color Change Button...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement colorChangebtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@id='colorChange']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", colorChangebtn);
//		js.executeScript("arguments[0].click();", ColorChange);
		JSclick.scrollAndClick(driver, ColorChange);
		
		demoqaLog.info("Clicked on Elements|Dynamic Properties|Color Change Button...");
		String buttonTxt = ColorChange.getText();
		System.out.println("Clicked on: " + buttonTxt);
		Assert.assertEquals("Color Change", buttonTxt);
	}
	
	public void ClickVisbleAfter() {
		demoqaLog.info("Checking for Elements|Dynamic Properties|Visble After 5 Seconds Button...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement visibleAfterbtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@id='visibleAfter']")));

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true);", visibleAfterbtn);
//		js.executeScript("arguments[0].click();", VisibleAfter);
		JSclick.scrollAndClick(driver, VisibleAfter);
		
		demoqaLog.info("Clicked on Elements|Dynamic Properties|Visble After 5 Seconds Button...");
		String buttonTxt = VisibleAfter.getText();
		System.out.println("Clicked on: " + buttonTxt);
		Assert.assertEquals("Visible After 5 Seconds", buttonTxt);
	}


}
