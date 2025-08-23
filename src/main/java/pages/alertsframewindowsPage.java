package pages;

import static org.testng.Assert.assertNotNull;

import java.time.Duration;
//import java.util.Set;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.demoqaBase;
import utils.JSclick;
import utils.WindowValidationUtils;
//import utils.demoqaLog;

public class alertsframewindowsPage extends demoqaBase {

	@FindBy(xpath = "//div[contains(@class,'col-12 mt-4 col-md-6')]")
	WebElement Cardpage;

	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'body-height')]/div[contains(@class,'container playgound-body')]/div[contains(@class,'row')]/div[contains(@class,'col-md-3')]/div[contains(@class,'left-pannel')]/div[contains(@class,'accordion')]/div[3]/span[1]/div[1]/div[1]")
	WebElement pageText;

	@FindBy(xpath = "//div[contains(@class,'element-list collapse show')]//li[@id='item-0']")
	WebElement BrowserWindows;

	@FindBy(xpath = "//h1[normalize-space()='Browser Windows']")
	WebElement BrowserWindowsPage;

	@FindBy(xpath = "//button[@id='tabButton']")
	WebElement NewTabBtn;

	@FindBy(xpath = "//h1[@id='sampleHeading']")
	WebElement NewTabresult;

	@FindBy(xpath = "//button[@id='windowButton']")
	WebElement NewWindowBtn;

	@FindBy(xpath = "//button[@id='messageWindowButton']")
	WebElement MessageWindowBtn;
	
	@FindBy(xpath = "/html/body/text()")
	WebElement MessageWindowBody;

	private WebDriverWait wait;

	public alertsframewindowsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		PageFactory.initElements(driver, this);
	}

	public void accessAlertFramesWindows() {

		demoqaLog.info("Accessing Alerts, Frame & Windows Card...");
		homePage homePage = new homePage(driver);
		System.out.println("Declared Instance WebDriverWait: " + wait);
		homePage.clickAlertsFrameWindowsCard();
		System.out.println("Title of this Page is: " + driver.getTitle());
		System.out.println("You can now access: " + pageText.getText());
		demoqaLog.info("Page: Alerts, Frame & Windows Card...");
		String cardpage = Cardpage.getText();

		Assert.assertEquals("Please select an item from left to start practice.", cardpage);
	}

	public void clickBrowserWindow() {
		demoqaLog.info("Clicking on Alerts, Frame & Windows Card|Browser Windows...");
		BrowserWindows.click();
		String browserWindowsPage = BrowserWindowsPage.getText();
		System.out.println("You are now Accessing: " + browserWindowsPage);
		demoqaLog.info("You are now Accessing: " + browserWindowsPage);

		Assert.assertEquals("Browser Windows", browserWindowsPage);
	}

	public void clickNewTab() {
		demoqaLog.info("Initiating New Tab click sequence...");

		String originalHandle = driver.getWindowHandle();
		assertNotNull("Original window handle is null", originalHandle);

		NewTabBtn.click();
		demoqaLog.info("Clicked New Tab button");

		String result = WindowValidationUtils.switchToNewTabAndValidateText(driver, originalHandle,
				By.xpath("//h1[@id='sampleHeading']"), "This is a sample page", demoqaLog);

		System.out.println("You are now accessing: " + result);
	}

	public void clickNewWindow() {
		demoqaLog.info("Initiating New Window click sequence...");

		String originalHandle = driver.getWindowHandle();
		assertNotNull("Original window handle is null", originalHandle);

		NewWindowBtn.click();
		demoqaLog.info("Clicked New Window button");

		String result = WindowValidationUtils.switchToNewTabAndValidateText(driver, originalHandle,
				By.xpath("//h1[@id='sampleHeading']"), "This is a sample page", demoqaLog);

		System.out.println("You are now accessing: " + result);
	}

	public void clickNewWindowMessage() {
		demoqaLog.info("🔍 Starting 'New Window Message' click sequence...");

		String originalHandle = driver.getWindowHandle();
		assertNotNull("Original window handle is null", originalHandle);

		JSclick.scrollAndClick(driver, MessageWindowBtn);
		
		String result = WindowValidationUtils.switchToNewWindowAndValidateText(
			    driver,
			    originalHandle,
			    "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.",
			    demoqaLog
			);

		System.out.println("You are now accessing: " + result);

	}
}
