package pages;

import static org.testng.Assert.assertNotNull;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.demoqaBase;
import utils.JSclick;
import utils.WindowValidationUtils;
//import utils.waitForElement;
import utils.waitForElement;

public class alertsframewindowsPage extends demoqaBase {

	@FindBy(xpath = "//div[contains(@class,'col-12 mt-4 col-md-6')]")
	WebElement Cardpage;

	@FindBy(xpath = "//body/div[@id='app']/div[contains(@class,'body-height')]/div[contains(@class,'container playgound-body')]/div[contains(@class,'row')]/div[contains(@class,'col-md-3')]/div[contains(@class,'left-pannel')]/div[contains(@class,'accordion')]/div[3]/span[1]/div[1]/div[1]")
	WebElement CardPgTitle;

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
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-2']")
	WebElement Frames;
	
	@FindBy(xpath = "//h1[normalize-space()='Frames']")
	WebElement FramepgTitle;
	
	@FindBy(id = "frame1")
	WebElement frame1;
	
	@FindBy(id = "frame2")
	WebElement frame2;
	
	@FindBy(id = "sampleHeading")
	WebElement frame1Content;
	
	@FindBy(id = "sampleHeading")
	WebElement frame2Content;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-3']")
	WebElement nestedFrames;
	
	@FindBy(xpath = "//h1[normalize-space()='Nested Frames']")
	WebElement nestedFramePgTitle;
	
	@FindBy(xpath = "//iframe[@id='frame1']")
	WebElement parentFrame;
	
	@FindBy(xpath = "//iframe[@srcdoc='<p>Child Iframe</p>']")
	WebElement childFrame;
	
	@FindBy(xpath = "//div[@class='element-list collapse show']//li[@id='item-4']")
	WebElement modalDialogs;
	
	@FindBy(xpath = "//h1[normalize-space()='Modal Dialogs']")
	WebElement ModalDiagPgTitle;
	
	@FindBy(xpath = "//button[@id='showSmallModal']")
	WebElement modalSmallBtn;
	
	@FindBy(xpath = "//div[@id='example-modal-sizes-title-sm']")
	WebElement smallModalTitle;
	
	@FindBy(xpath = "//div[@class='modal-body']")
	WebElement smallModalBody;
	
	@FindBy(xpath = "//button[@id='closeSmallModal']")
	WebElement smallModalCloseBtn;
	
	@FindBy(xpath = "//button[@id='showLargeModal']")
	WebElement modalLargeBtn;
	
	@FindBy(xpath = "//div[@id='example-modal-sizes-title-lg']")
	WebElement largeModalTitle;
	
	@FindBy(xpath = "//p[contains(text(),'Lorem Ipsum is simply dummy text of the printing a')]")
	WebElement largeModalBody;
	
	@FindBy(xpath = "//button[@id='closeLargeModal']")
	WebElement largeModalCloseBtn;
	
	
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
		System.out.println("You are Accessing: " + CardPgTitle.getText());
		String cardpage = Cardpage.getText();
		demoqaLog.info("Alerts, Frame & Windows Card: " + cardpage);

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
	
	public void clickFrames() {
	    demoqaLog.info("🔍 Starting test for Frames click ..");
	    waitForElement.waitUntilInteractable(driver, Frames);
	    JSclick.scrollAndClick(driver, Frames);
	    demoqaLog.info("✅ Clicked on Frames...");
	    String framesPgTitle = FramepgTitle.getText();
	    demoqaLog.info("Page Title is: " + framesPgTitle);
	    
	    Assert.assertEquals("Frames", framesPgTitle);
	}
	
	public void GetFrame1msg() {
	    demoqaLog.info("🔍 Starting test for Frames|Frame1 Message...");

	    By frame1Locator = By.id("frame1"); // Confirmed from demoqa.com/frames
	    boolean frameReady = waitForElement.waitForFrameAndSwitch(driver, frame1Locator, 10);

	    if (!frameReady) {
	        demoqaLog.error("❌ Frame1 not available or not loaded in time.");
	        Assert.fail("Frame1 not found or not ready.");
	    }

	    demoqaLog.info("✅ Switched to Frame1...");

	    // Locate the content inside the frame AFTER switching
	    By contentLocator = By.id("sampleHeading");
	    WebElement frame1Content;
	    try {
	        frame1Content = new WebDriverWait(driver, Duration.ofSeconds(5))
	                .until(ExpectedConditions.visibilityOfElementLocated(contentLocator));
	    } catch (TimeoutException e) {
	        demoqaLog.error("❌ Frame1 content not visible in time.");
	        Assert.fail("Frame1 content not loaded: " + e.getMessage());
	        return;
	    }

	    String frame1msg = frame1Content.getText();
	    demoqaLog.info("Frame1 contains: " + frame1msg);

	    Assert.assertEquals("This is a sample page", frame1msg);
	    driver.switchTo().defaultContent();
	}
	
	public void GetFrame2msg() {
	    demoqaLog.info("🔍 Starting test for Frames|Frame2 Message...");

	    By frame2Locator = By.id("frame2"); // Confirmed from demoqa.com/frames
	    boolean frameReady = waitForElement.waitForFrameAndSwitch(driver, frame2Locator, 10);

	    if (!frameReady) {
	        demoqaLog.error("❌ Frame2 not available or not loaded in time.");
	        Assert.fail("Frame2 not found or not ready.");
	    }

	    demoqaLog.info("✅ Switched to Frame2...");

	    // Locate the content inside the frame AFTER switching
	    By contentLocator = By.id("sampleHeading");
	    WebElement frame2Content;
	    try {
	        frame2Content = new WebDriverWait(driver, Duration.ofSeconds(5))
	                .until(ExpectedConditions.visibilityOfElementLocated(contentLocator));
	    } catch (TimeoutException e) {
	        demoqaLog.error("❌ Frame2 content not visible in time.");
	        Assert.fail("Frame2 content not loaded: " + e.getMessage());
	        return;
	    }

	    String frame2msg = frame2Content.getText();
	    demoqaLog.info("Frame2 contains: " + frame2msg);

	    Assert.assertEquals("This is a sample page", frame2msg);
	    driver.switchTo().defaultContent();
	}
	
	public void clickNestedFrames() {
		demoqaLog.info("🔍 Starting test for Frames|Nested Frames click ..");

	    JSclick.scrollAndClick(driver, nestedFrames);
	    demoqaLog.info("✅ Clicked on Nested Frames...");
	    String nestedframesPgTitle = nestedFramePgTitle.getText();
	    demoqaLog.info("Page Title is: " + nestedframesPgTitle);
	    
	    Assert.assertEquals("Nested Frames", nestedframesPgTitle);
	}

	
	public void GetParentFramemsg() {
	    demoqaLog.info("🔍 Starting test for Frames|Parent Frame Message...");
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    driver.switchTo().frame(parentFrame);
        Assert.assertEquals(true, driver.getPageSource().contains("Parent frame"));
        String frameText = driver.findElement(By.tagName("body")).getText().trim();
        demoqaLog.info("After switching, frame body contains: [" + frameText + "]");
        
        Assert.assertTrue(frameText.contains("Parent frame"), "Unexpected frame content.");
	}
	
	public void GetChildFramemsg() {
	    demoqaLog.info("🔍 Starting test for Frames|Child Frame Message...");
	    driver.switchTo().frame(parentFrame);
        Assert.assertEquals(true, driver.getPageSource().contains("Parent frame"));

	    driver.switchTo().frame(childFrame);
        Assert.assertEquals(true, driver.getPageSource().contains("Child Iframe"));
        String frameText = driver.findElement(By.tagName("body")).getText().trim();
        demoqaLog.info("After switching, frame body contains: [" + frameText + "]");
        
        Assert.assertTrue(frameText.contains("Child Iframe"), "Unexpected frame content.");
	}
	
	public void clickModalDialogs() {
		demoqaLog.info("🔍 Starting test for Frames|Modal Dialogs click ..");

	    JSclick.scrollAndClick(driver, modalDialogs);
	    demoqaLog.info("✅ Clicked on Modal Dialogs...");
	    
	    String modalDialogsPgTitle = ModalDiagPgTitle.getText();
	    demoqaLog.info("Page Title is: " + modalDialogsPgTitle);
	    
	    Assert.assertEquals("Modal Dialogs", modalDialogsPgTitle);
	}
	
	public void clickSmallModal() {
		demoqaLog.info("🔍 Starting test for Frames|Modal Dialogs|Small Modal click...");

	    JSclick.scrollAndClick(driver, modalSmallBtn);
	    demoqaLog.info("✅ Clicked on Modal Dialogs|Small Modal...");
	    
	    String smallmodalPgTitle = smallModalTitle.getText();
	    demoqaLog.info("Page Title is: " + smallmodalPgTitle);
	    
	    Assert.assertEquals("Small Modal", smallmodalPgTitle);
	    
	    String smallmodalBody = smallModalBody.getText();
	    demoqaLog.info("Small Modal Body Contains: " + smallmodalBody);
	    
	    Assert.assertTrue(smallmodalBody.contains("This is a small modal"), "Unexpected frame content.");
	    JSclick.scrollAndClick(driver, smallModalCloseBtn);
	}
	
	public void clickLargeModal() {
		demoqaLog.info("🔍 Starting test for Frames|Modal Dialogs|Large Modal click...");

	    JSclick.scrollAndClick(driver, modalLargeBtn);
	    demoqaLog.info("✅ Clicked on Modal Dialogs|Large Modal...");
	    
	    String largemodalPgTitle = largeModalTitle.getText();
	    demoqaLog.info("Page Title is: " + largemodalPgTitle);
	    
	    Assert.assertEquals("Large Modal", largemodalPgTitle);
	    
	    String largemodalBody = largeModalBody.getText();
	    demoqaLog.info("Large Modal Body Contains: " + largemodalBody);
	    
	    Assert.assertTrue(largemodalBody.contains("Lorem Ipsum is simply dummy text"), "Unexpected frame content.");
	    JSclick.scrollAndClick(driver, largeModalCloseBtn);
	}
	
}
