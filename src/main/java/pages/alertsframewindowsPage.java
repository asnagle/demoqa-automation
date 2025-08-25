package pages;

import static org.testng.Assert.assertNotNull;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
//import java.util.Set;

import org.openqa.selenium.Alert;
//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-1']")
	WebElement Alerts;
	
	@FindBy(xpath = "//h1[normalize-space()='Alerts']")
	WebElement AlertsPageTitle;
	
	@FindBy(xpath = "//button[@id='alertButton']")
	WebElement seeAlertBtn;
	
	@FindBy(xpath = "//button[@id='timerAlertButton']")
	WebElement timerAlertButton;
	
	@FindBy(xpath = "//button[@id='confirmButton']")
	WebElement confirmboxButton;
	
	@FindBy(xpath = "//span[@id='confirmResult']")
	WebElement confirmResult;
	
	@FindBy(xpath = "//button[@id='promtButton']")
	WebElement promptBox;
	
	@FindBy(xpath = "//span[@id='promptResult']")
	WebElement promptResult;

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

	    // Get original handle
	    String originalHandle = driver.getWindowHandle();
	    assertNotNull("Original window handle is null", originalHandle);

	    // Click on the button
	    JSclick.scrollAndClick(driver, MessageWindowBtn);

	    // Validate new window opened and return to original
	    WindowValidationUtils.switchToNewWindowAndValidate(
	        driver,
	        originalHandle,
	        demoqaLog
	    );

	    demoqaLog.info("✅ 'New Window Message' flow completed successfully.");
	}
	
	public void clickAlerts() {
	    demoqaLog.info("🔍 Starting test for Alerts click ..");

	    JSclick.scrollAndClick(driver, Alerts);
	    demoqaLog.info("✅ Clicked on Alerts...");
	    String alertsPgTitle = AlertsPageTitle.getText();
	    demoqaLog.info("Page Title is: " + alertsPgTitle);
	    
	    Assert.assertEquals("Alerts", alertsPgTitle);
	}
	
	public void clickToSeeAlert() {
		demoqaLog.info("🔍 Starting test for Click Button to see Alert...");

	    JSclick.scrollAndClick(driver, seeAlertBtn);
	    demoqaLog.info("✅ Clicked on Click Button to see Alert...");
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ Click Button to see Alert message is: " + alert.getText());
        
        Assert.assertEquals("You clicked a button", alert.getText());
        alert.accept();
	}
	
	public void clickSeeAlert5sec() {
		demoqaLog.info("🔍 Starting test for On button click, alert will appear after 5 seconds...");

	    JSclick.scrollAndClick(driver, timerAlertButton);
	    demoqaLog.info("✅ Clicked on Click Button to see Alert after 5 seconds...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, alert will appear after 5 seconds, message is: " + alert.getText());
        
        Assert.assertEquals("This alert appeared after 5 seconds", alert.getText());
        alert.accept();
	}
	
	public void clickConfirmBoxAlertAccept() {
		demoqaLog.info("🔍 Starting test for On button click, confirm box will appear, Accept...");

	    JSclick.scrollAndClick(driver, confirmboxButton);
	    demoqaLog.info("✅ Clicked On button click, confirm box will appear, Accept...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, confirm box will appear, message is: " + alert.getText());
        alert.accept();
        String confResult = confirmResult.getText();
        demoqaLog.info("✅ Your Selection was: " + confResult);
        
        Assert.assertEquals("You selected Ok", confResult);
	}
	
	public void clickConfirmBoxAlertDeny() {
		demoqaLog.info("🔍 Starting test for On button click, confirm box will appear, Deny...");

	    JSclick.scrollAndClick(driver, confirmboxButton);
	    demoqaLog.info("✅ Clicked On button click, confirm box will appear, Deny...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, confirm box will appear, message is: " + alert.getText());
        alert.dismiss();
        String confResult = confirmResult.getText();
        demoqaLog.info("✅ Your Selection was: " + confResult);
        
        Assert.assertEquals("You selected Cancel", confResult);
	}
	
	public void clickPromptFill() {
		demoqaLog.info("🔍 Starting test for On button click, prompt box will appear, Fill data...");
	    JSclick.scrollAndClick(driver, promptBox);
	    
	    demoqaLog.info("✅ Clicked On button click, prompt box will appear, Fill data...");
	    Alert alert = driver.switchTo().alert();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        demoqaLog.info("✅ On button click, prompt box will appear, message is: " + alert.getText());
        Assert.assertEquals("Please enter your name", alert.getText());
        alert.sendKeys("Java Selenium");
        alert.accept();
        String promptRslt = promptResult.getText();
        demoqaLog.info("✅ Your Selection was: " + promptRslt);
        
        Assert.assertEquals("You entered Java Selenium", promptRslt);
	}
	
}
